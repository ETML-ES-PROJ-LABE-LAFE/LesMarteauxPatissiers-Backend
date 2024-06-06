package ch.etmles.auction.Mappers;

import ch.etmles.auction.Entities.Bid;
import ch.etmles.auction.DTOs.BidDTO;
import org.springframework.stereotype.Component;

@Component
public class BidMapper {

    public BidDTO toDto(Bid bid) {
        if (bid == null) {
            return null;
        }

        BidDTO bidDTO = new BidDTO();
        bidDTO.setId(bid.getId());
        bidDTO.setItemId(bid.getItem().getId());
        bidDTO.setAppUserId(bid.getAppUser().getId());
        bidDTO.setAmount(bid.getAmount());
        bidDTO.setBidTime(bid.getBidTime());
        return bidDTO;
    }

    public Bid toEntity(BidDTO bidDTO) {
        if (bidDTO == null) {
            return null;
        }

        Bid bid = new Bid();
        bid.setId(bidDTO.getId());
        bid.setAmount(bidDTO.getAmount());
        bid.setBidTime(bidDTO.getBidTime());
        // Set item and appUser in service
        return bid;
    }
}
