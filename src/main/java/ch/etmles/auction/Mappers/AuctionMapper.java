package ch.etmles.auction.Mappers;

import ch.etmles.auction.Entities.Auction;
import ch.etmles.auction.DTOs.AuctionDTO;
import ch.etmles.auction.Entities.Bid;
import ch.etmles.auction.Repositories.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuctionMapper {

    public AuctionDTO toDto(Auction auction) {
        if (auction == null) {
            return null;
        }
        AuctionDTO auctionDTO = new AuctionDTO();
        auctionDTO.setId(auction.getId());
        auctionDTO.setItemId(auction.getItem().getId());
        List<Long> bidsList = auction.getBids().stream().map(Bid::getId).collect(Collectors.toList());
        auctionDTO.setBids(bidsList);
        auctionDTO.setIsActive(auction.isActive());
        auctionDTO.setDesactivatedTime(auction.getDesactivationDate());
        return auctionDTO;
    }

    public Auction toEntity(AuctionDTO auctionDTO) {
        if (auctionDTO == null) {
            return null;
        }
        Auction auction = new Auction();
        auction.setId(auctionDTO.getId());
        auction.setActive(auctionDTO.isActive());
        auction.setDesactivationDate(auctionDTO.getDesactivatedTime());
        // Les champs item et bids sont définis dans le service
        return auction;
    }
}
