package ch.etmles.auction.Controllers;

import ch.etmles.auction.DTOs.AuctionDTO;
import ch.etmles.auction.DTOs.BidDTO;
import ch.etmles.auction.DTOs.ItemDTO;
import ch.etmles.auction.Services.AuctionService;
import ch.etmles.auction.Services.BidService;
import ch.etmles.auction.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private BidService bidService;
    @Autowired
    private AuctionService auctionService;

    @GetMapping
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    /* exemple d'envoi avec curl
    curl -i -X POST http://localhost:8080/api/items ^
    -H "Content-Type: application/json" ^
    -d "{\"name\": \"New Item Name\", \"categoryId\": 1, \"description\": \"Description of the new item\", \"initialPrice\": 150.0, \"lastBid\": 0.0}"
     */
    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) {
        ItemDTO savedItem = itemService.createItem(itemDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    //get all bids by item id
    @GetMapping("/{id}/bids")
    public ResponseEntity<List<BidDTO>> getBidsByItem(@PathVariable long id) {
        List<BidDTO> bids = bidService.getBidsByItemId(id);
        return !bids.isEmpty() ? ResponseEntity.ok(bids) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/auction")
    public ResponseEntity<List<AuctionDTO>> getAuctionsByItem(@PathVariable long id) {
        List<AuctionDTO> auctions = auctionService.getAuctionsByItemId(id);
        return !auctions.isEmpty() ? ResponseEntity.ok(auctions) : ResponseEntity.noContent().build();
    }

    /* exemple d'envoi avec curl
    curl -i -X PUT http://localhost:8080/api/items/71 ^
        -H "Content-Type: application/json" ^
        -d "{\"name\": \"Updated Item Name\", \"categoryId\": 1, \"description\": \"Updated description of the item\", \"initialPrice\": 200.0, \"lastBid\": 250.0}"
     */
    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateOrCreateItem(@PathVariable long id, @RequestBody ItemDTO itemDTO) {
        boolean exists = itemService.existsById(id);
        ItemDTO savedItem = itemService.updateOrCreateItem(id, itemDTO);
        HttpStatus status = exists ? HttpStatus.OK : HttpStatus.CREATED;
        return ResponseEntity.status(status).body(savedItem);
    }

    //TODO: test on exception
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        itemService.deleteItem(id);
        return ResponseEntity.ok().build();
    }


}
