package ch.etmles.auction.Controllers;

import ch.etmles.auction.DTOs.BidDTO;
import ch.etmles.auction.Services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bids")
public class BidController {

    @Autowired
    private BidService bidService;

    @GetMapping
    public List<BidDTO> getAllBids() {
        return bidService.getAllBids();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BidDTO> getBidById(@PathVariable long id) {
        BidDTO bidDTO = bidService.getBidById(id);
        if (bidDTO != null) {
            return ResponseEntity.ok(bidDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public BidDTO createBid(@RequestBody BidDTO bidDTO) {
        return bidService.createBid(bidDTO);
    }


}
