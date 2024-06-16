package ch.etmles.auction.Services;

import ch.etmles.auction.Entities.AppUser;
import ch.etmles.auction.Entities.Auction;
import ch.etmles.auction.Entities.Bid;
import ch.etmles.auction.Entities.Item;
import ch.etmles.auction.Exceptions.AuctionAlreadyActiveException;
import ch.etmles.auction.Exceptions.AuctionNotActiveException;
import ch.etmles.auction.Exceptions.AuctionNotFoundException;
import ch.etmles.auction.Exceptions.ItemNotFoundException;
import ch.etmles.auction.Repositories.AppUserRepository;
import ch.etmles.auction.Repositories.AuctionRepository;
import ch.etmles.auction.Repositories.ItemRepository;
import ch.etmles.auction.DTOs.AuctionDTO;
import ch.etmles.auction.Mappers.AuctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuctionMapper auctionMapper;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private ItemRepository itemRepository;

    public List<AuctionDTO> getAllAuctions() {
        List<Auction> auctions = auctionRepository.findAll();
        return auctions.stream().map(auctionMapper::toDto).collect(Collectors.toList());
    }

    public AuctionDTO getAuctionById(long id) {
        Auction auction = auctionRepository.findById(id).orElseThrow(()-> new AuctionNotFoundException(id));
        return auctionMapper.toDto(auction);
    }

    public AuctionDTO createAuction(AuctionDTO auctionDTO) {

        Auction auction = auctionMapper.toEntity(auctionDTO);

        Item item = itemRepository.findById(auctionDTO.getItemId())
                .orElseThrow(() -> new ItemNotFoundException(auctionDTO.getItemId())) ;

        //control if there is an active auction for the current item
        Auction existingActiveAuctions = auctionRepository.findActiveAuctionByItemId(auctionDTO.getItemId());
        if ((existingActiveAuctions != null)) {
            throw new AuctionAlreadyActiveException(existingActiveAuctions.getId());
        }

        auction.setItem(item);
        auction.setActive(true);
        Auction savedAuction = auctionRepository.save(auction);
        return auctionMapper.toDto(savedAuction);
    }

    public void deactivateAuction(long id) {
        Auction auction = auctionRepository.findById(id)
                .orElseThrow(() -> new AuctionNotFoundException(id));
        if (!auction.isActive()) {
            throw new AuctionNotActiveException(auction.getId());
        }
        auction.setActive(false);
        auction.setDesactivationDate(LocalDateTime.now());
        AppUser appUser = auction.getItem().getAppUser();
        if (!auction.getBids().isEmpty()) {
            Bid lastBid = auction.getBids().get(auction.getBids().size() - 1);
            appUser.setCredit(appUser.getCredit().add(lastBid.getAmount()));
            appUserRepository.save(appUser);
        }
        Auction updatedAuction = auctionRepository.save(auction);
        auctionMapper.toDto(updatedAuction);
    }

    public List<AuctionDTO> getAuctionsByItemId(long itemId) {
        List<Auction> auctions = auctionRepository.findByItemId(itemId);
        return auctions.stream().map(auctionMapper::toDto).collect(Collectors.toList());
    }

}
