package ch.etmles.auction.Controllers;

import ch.etmles.auction.DTOs.AppUserDTO;
import ch.etmles.auction.DTOs.AuctionDTO;
import ch.etmles.auction.DTOs.ItemDTO;
import ch.etmles.auction.Entities.Auction;
import ch.etmles.auction.Mappers.ItemMapper;
import ch.etmles.auction.Services.AppUserService;
import ch.etmles.auction.Services.AuctionService;
import ch.etmles.auction.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class AppUserController {


    @Autowired
    AppUserService appUserService;

    @Autowired
    ItemService itemService;
    @Autowired
    private ItemMapper itemMapper;

    @GetMapping
    public List<AppUserDTO> getAllUsers() {
        return appUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable Long id) {
        AppUserDTO userDTO = appUserService.getUserById(id);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public AppUserDTO createUser(@RequestBody AppUserDTO appUserDTO) {
        return appUserService.createUser(appUserDTO);
    }

    @GetMapping("/{id}/purchases")
    public ResponseEntity<List<ItemDTO>> getSoldAuctionByUserId(@PathVariable Long id) {
        List<ItemDTO> items = itemService.findItemsByBuyer(id);
        return !items.isEmpty() ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/sales")
    public ResponseEntity<List<ItemDTO>> getBoughtAuctionByUserId(@PathVariable Long id) {
        List<ItemDTO> items = itemService.findItemsBySeller(id);
        return !items.isEmpty() ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
    }
}
