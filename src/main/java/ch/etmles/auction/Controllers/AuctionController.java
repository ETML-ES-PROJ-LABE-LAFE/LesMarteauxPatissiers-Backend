package ch.etmles.auction.Controllers;

import ch.etmles.auction.DTOs.AuctionDTO;
import ch.etmles.auction.Services.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auctions")
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping
    public ResponseEntity<List<AuctionDTO>> getAllAuctions() {
        List<AuctionDTO> auctionList = auctionService.getAllAuctions();
        if (auctionList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auctionList) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuctionDTO> getAuctionById(@PathVariable long id) {
        AuctionDTO auctionDTO = auctionService.getAuctionById(id);
        if (auctionDTO != null) {
            return ResponseEntity.ok(auctionDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<AuctionDTO> createAuction(@RequestBody AuctionDTO auctionDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(auctionService.createAuction(auctionDTO));
    }

    //endpoint to soft delete auction -> no response
    @PutMapping("/{id}/desactivate")
    public ResponseEntity<AuctionDTO> deactivateAuction(@PathVariable long id) {
        try {
            auctionService.deactivateAuction(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.noContent().build();
        }
    }
}

