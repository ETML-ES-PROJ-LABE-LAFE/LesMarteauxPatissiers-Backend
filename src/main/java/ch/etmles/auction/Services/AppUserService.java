package ch.etmles.auction.Services;

import ch.etmles.auction.Entities.AppUser;
import ch.etmles.auction.Exceptions.AppUserNotFoundException;
import ch.etmles.auction.Repositories.AppUserRepository;
import ch.etmles.auction.DTOs.AppUserDTO;
import ch.etmles.auction.Mappers.AppUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
        AppUser user = appUserRepository.findById(id).orElseThrow(() -> new AppUserNotFoundException(id));
        return appUserMapper.toDto(user);
    }

    public AppUserDTO createUser(AppUserDTO appUserDTO) {
        AppUser user = appUserMapper.toEntity(appUserDTO);
        user = appUserRepository.save(user);
        return appUserMapper.toDto(user);
    }


}
