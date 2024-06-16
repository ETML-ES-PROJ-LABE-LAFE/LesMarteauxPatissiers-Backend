package ch.etmles.auction.Controllers;

import ch.etmles.auction.DTOs.BidDTO;
import ch.etmles.auction.Services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return ResponseEntity.ok(bidDTO);
    }

    @PostMapping
    public ResponseEntity<BidDTO> createBid(@RequestBody BidDTO bidDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bidService.createBid(bidDTO));
    }
}
