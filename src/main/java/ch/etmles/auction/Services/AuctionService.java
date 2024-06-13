package ch.etmles.auction.Services;

import ch.etmles.auction.Entities.Auction;
import ch.etmles.auction.Entities.Item;
import ch.etmles.auction.Repositories.AuctionRepository;
import ch.etmles.auction.Repositories.ItemRepository;
import ch.etmles.auction.DTOs.AuctionDTO;
import ch.etmles.auction.Mappers.AuctionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuctionMapper auctionMapper;

    @Autowired
    private ItemRepository itemRepository;

    public List<AuctionDTO> getAllAuctions() {
        List<Auction> auctions = auctionRepository.findAll();
        return auctions.stream().map(auctionMapper::toDto).collect(Collectors.toList());
    }

    public AuctionDTO getAuctionById(long id) {
        Optional<Auction> auction = auctionRepository.findById(id);
        return auction.map(auctionMapper::toDto).orElse(null);
    }

    public AuctionDTO createAuction(AuctionDTO auctionDTO) {

        Auction auction = auctionMapper.toEntity(auctionDTO);

        Item item = itemRepository.findById(auctionDTO.getItemId())
                .orElseThrow(() -> new RuntimeException("Item not found with id: " + auctionDTO.getItemId()));

        //control if there is an active auction for the current item
        List<Auction> existingActiveAuctions = auctionRepository.findActiveAuctionsByItemId(auctionDTO.getItemId());
        if (!(existingActiveAuctions.isEmpty())) {
            throw new RuntimeException("An auction for this item is already active with id : " + existingActiveAuctions.get(0).getId());
        }

        auction.setItem(item);
        auction.setActive(true);
        Auction savedAuction = auctionRepository.save(auction);
        return auctionMapper.toDto(savedAuction);
    }

    public void deactivateAuction(long id) {
        Auction auction = auctionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Auction not found with id: " + id));
        auction.setActive(false);
        auction.setDesactivationDate(LocalDateTime.now());
        Auction updatedAuction = auctionRepository.save(auction);
        auctionMapper.toDto(updatedAuction);
    }

    public List<AuctionDTO> getAuctionsByItemId(long itemId) {
        List<Auction> auctions = auctionRepository.findByItemId(itemId);
        return auctions.stream().map(auctionMapper::toDto).collect(Collectors.toList());
    }

}
