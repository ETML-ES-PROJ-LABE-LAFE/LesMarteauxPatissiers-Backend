package ch.etmles.auction.Controllers;

import ch.etmles.auction.DTOs.AppUserDTO;
import ch.etmles.auction.Repositories.AppUserRepository;
import ch.etmles.auction.Services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class AppUserController {


    @Autowired
    AppUserService appUserService;


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
}
