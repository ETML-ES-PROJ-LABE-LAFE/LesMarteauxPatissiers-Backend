package ch.etmles.auction.Controllers;

import ch.etmles.auction.DTOs.AppUserDTO;
import ch.etmles.auction.DTOs.ItemDTO;
import ch.etmles.auction.Services.AppUserService;
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
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public AppUserDTO createUser(@RequestBody AppUserDTO appUserDTO) {
        return appUserService.createUser(appUserDTO);
    }

    @GetMapping("/{id}/purchases")
    public ResponseEntity<List<ItemDTO>> getPurchasesByUserId(@PathVariable Long id) {
        List<ItemDTO> items = itemService.findItemsByBuyer(id);
        return !items.isEmpty() ? ResponseEntity.ok(items) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/sales")
    public ResponseEntity<List<ItemDTO>> getSalesByUserId(@PathVariable Long id) {
        List<ItemDTO> items = itemService.findItemsBySeller(id);
        return !items.isEmpty() ? ResponseEntity.ok(items) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/won")
    public ResponseEntity<List<ItemDTO>> getWonAuctionByUserId(@PathVariable Long id) {
        List<ItemDTO> items = itemService.findItemsWonByUser(id);
        return !items.isEmpty() ? ResponseEntity.ok(items) : ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/sold")
    public ResponseEntity<List<ItemDTO>> getSoldAuctionByUserId(@PathVariable Long id) {
        List<ItemDTO> items = itemService.findItemsSoldByUser(id);
        return !items.isEmpty() ? ResponseEntity.ok(items) : ResponseEntity.noContent().build();
    }

}
