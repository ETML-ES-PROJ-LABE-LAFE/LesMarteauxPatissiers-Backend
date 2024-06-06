package ch.etmles.auction.Services;

import ch.etmles.auction.Entities.Bid;
import ch.etmles.auction.Entities.Item;
import ch.etmles.auction.Entities.AppUser;
import ch.etmles.auction.Repositories.BidRepository;
import ch.etmles.auction.Repositories.ItemRepository;
import ch.etmles.auction.Repositories.AppUserRepository;
import ch.etmles.auction.DTOs.BidDTO;
import ch.etmles.auction.Mappers.BidMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        // Récupérer Item et AppUser
        Item item = itemRepository.findById(bidDTO.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + bidDTO.getItemId()));
        AppUser appUser = appUserRepository.findById(bidDTO.getAppUserId())
                .orElseThrow(() -> new RuntimeException("AppUser not found with id: " + bidDTO.getAppUserId()));

        bid.setItem(item);
        bid.setAppUser(appUser);

        Bid savedBid = bidRepository.save(bid);
        return bidMapper.toDto(savedBid);
    }


}
