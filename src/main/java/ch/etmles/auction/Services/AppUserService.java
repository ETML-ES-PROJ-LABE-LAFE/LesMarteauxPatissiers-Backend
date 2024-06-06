package ch.etmles.auction.Services;

import ch.etmles.auction.Entities.AppUser;
import ch.etmles.auction.Repositories.AppUserRepository;
import ch.etmles.auction.DTOs.AppUserDTO;
import ch.etmles.auction.Mappers.AppUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppUserService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AppUserMapper appUserMapper;

    public List<AppUserDTO> getAllUsers() {
        List<AppUser> users = appUserRepository.findAll();
        return users.stream()
                .map(appUserMapper::toDto)
                .collect(Collectors.toList());
    }

    public AppUserDTO getUserById(Long id) {
        Optional<AppUser> user = appUserRepository.findById(id);
        return user.map(appUserMapper::toDto).orElse(null);
    }

    public AppUserDTO createUser(AppUserDTO appUserDTO) {
        AppUser user = appUserMapper.toEntity(appUserDTO);
        user = appUserRepository.save(user);
        return appUserMapper.toDto(user);
    }

    public AppUserDTO updateUser(Long id, AppUserDTO appUserDTO) {
        Optional<AppUser> userOptional = appUserRepository.findById(id);
        if (userOptional.isPresent()) {
            AppUser user = userOptional.get();
            user.setNom(appUserDTO.getNom());
            user.setPrenom(appUserDTO.getPrenom());
            user.setCredit(appUserDTO.getCredit());
            user = appUserRepository.save(user);
            return appUserMapper.toDto(user);
        } else {
            return null;
        }
    }

    public boolean deleteUser(Long id) {
        Optional<AppUser> userOptional = appUserRepository.findById(id);
        if (userOptional.isPresent()) {
            appUserRepository.delete(userOptional.get());
            return true;
        } else {
            return false;
        }
    }
}
