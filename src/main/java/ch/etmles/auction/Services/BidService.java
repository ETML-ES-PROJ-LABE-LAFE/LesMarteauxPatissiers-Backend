package ch.etmles.auction.Services;

import ch.etmles.auction.Entities.Auction;
import ch.etmles.auction.Entities.Bid;
import ch.etmles.auction.Entities.Item;
import ch.etmles.auction.Entities.AppUser;
import ch.etmles.auction.Exceptions.*;
import ch.etmles.auction.Repositories.BidRepository;
import ch.etmles.auction.Repositories.AuctionRepository;
import ch.etmles.auction.Repositories.ItemRepository;
import ch.etmles.auction.Repositories.AppUserRepository;
import ch.etmles.auction.DTOs.BidDTO;
import ch.etmles.auction.Mappers.BidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private BidMapper bidMapper;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    public List<BidDTO> getAllBids() {
        List<Bid> bids = bidRepository.findAll();
        return bids.stream().map(bidMapper::toDto).collect(Collectors.toList());
    }

    public BidDTO getBidById(long id) {
        Optional<Bid> bid = bidRepository.findById(id);
        return bid.map(bidMapper::toDto).orElse(null);
    }

    public BidDTO createBid(BidDTO bidDTO) {
        Bid bid = bidMapper.toEntity(bidDTO);
        Auction auction = auctionRepository.findById(bidDTO.getAuctionId())
                .orElseThrow(() -> new AuctionNotFoundException(bidDTO.getAuctionId()));
        if (!(auction.isActive())) {
            throw new AuctionNotActiveException(bidDTO.getAuctionId());
        }
        if (!auction.getBids().isEmpty()) {
            Bid lastBid = auction.getBids().get(auction.getBids().size() - 1);
            if (!(bidDTO.getAmount().compareTo(lastBid.getAmount()) > 0)) {
                // si le montant de la mise n'est pas plus grand que la dernière mise
                throw new BidTooLowException(lastBid.getAmount());
            }
        }
        Item item = itemRepository.findById(bidDTO.getItemId())
                .orElseThrow(() -> new ItemNotFoundException(bidDTO.getItemId()));
        if (item.getId() != auction.getItem().getId()) {
            throw new ItemMismatchException(bidDTO.getItemId());
        }

        if (!(bidDTO.getAmount().compareTo(item.getInitialPrice()) >= 0)) {
            throw new BidTooLowException(item.getInitialPrice());
        }

        AppUser appUser = appUserRepository.findById(bidDTO.getAppUserId())
                .orElseThrow(() -> new AppUserNotFoundException(bidDTO.getAppUserId())) ;
        if (appUser == auction.getItem().getAppUser()) {
            throw new BidNotAllowedException();
        }
        if (!(bidDTO.getAmount().compareTo(appUser.getCredit()) > 0)) {
            try {
                //on soustrait le montant de la mise au total disponible du user pour mettre à jour son crédit après la mise
                appUser.setCredit(appUser.getCredit().subtract(bidDTO.getAmount()));

                // on retroverse le montant de la mise précédente au user qui l'a effectué
                if (!auction.getBids().isEmpty()) {
                    Bid lastBid = auction.getBids().get(auction.getBids().size() - 1);
                    AppUser oldBidder = lastBid.getAppUser();
                    oldBidder.setCredit(oldBidder.getCredit().add(lastBid.getAmount()));
                }
                // on enregistre la nouvelle mise
                bid.setItem(item);
                bid.setAppUser(appUser);
                bid.setAuction(auction);
                bid.setBidTime(LocalDateTime.now());
                auction.addBid(bid);  // Ajouter la mise à l'enchère
                Bid savedBid = bidRepository.save(bid);
                return bidMapper.toDto(savedBid);
            } catch (Exception e) {
                throw new RuntimeException(e + " : au secours !!!");
            }
        }
        throw new InsufficientCreditException(appUser.getId(), appUser.getCredit()) ;
    }

    public List<BidDTO> getBidsByItemId(long itemId) {
        List<Bid> bids = bidRepository.findByItemId(itemId);
        return bids.stream().map(bidMapper::toDto).collect(Collectors.toList());
    }

}
