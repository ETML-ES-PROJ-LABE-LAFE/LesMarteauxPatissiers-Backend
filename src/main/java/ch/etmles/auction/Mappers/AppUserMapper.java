package ch.etmles.auction.Mappers;

import ch.etmles.auction.Entities.AppUser;
import ch.etmles.auction.DTOs.AppUserDTO;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {

    public AppUserDTO toDto(AppUser appUser) {
        if (appUser == null) {
            return null;
        }

        return new AppUserDTO(
                appUser.getId(),
                appUser.getNom(),
                appUser.getPrenom(),
                appUser.getCredit()
        );
    }

    public AppUser toEntity(AppUserDTO appUserDTO) {
        if (appUserDTO == null) {
            return null;
        }

        return new AppUser(
                appUserDTO.getNom(),
                appUserDTO.getPrenom(),
                appUserDTO.getCredit()
        );
    }
}

