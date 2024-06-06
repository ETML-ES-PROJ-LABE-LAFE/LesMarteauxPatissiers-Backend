package ch.etmles.auction.Mappers;

import ch.etmles.auction.DTOs.ItemDTO;
import ch.etmles.auction.Entities.AppUser;
import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Entities.Item;
import ch.etmles.auction.Repositories.AppUserRepository;
import ch.etmles.auction.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ItemDTO convertToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setReference(item.getReference());
        itemDTO.setName(item.getName());
        itemDTO.setCategoryId(item.getCategory().getId());
        itemDTO.setAppUserId(item.getAppUser().getId());
        itemDTO.setDescription(item.getDescription());
        itemDTO.setImageName(item.getImage());
        itemDTO.setInitialPrice(item.getInitialPrice());
        itemDTO.setLastBid(item.getLastBid());
        return itemDTO;
    }

    public Item convertToEntity(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setReference(itemDTO.getReference());
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setImage(itemDTO.getImageName());
        item.setInitialPrice(itemDTO.getInitialPrice());
        item.setLastBid(itemDTO.getLastBid());

        Category category = categoryRepository.findById(itemDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found with id: " + itemDTO.getCategoryId()));
        item.setCategory(category);

        AppUser appUser = appUserRepository.findById(itemDTO.getAppUserId())
                .orElseThrow(() -> new RuntimeException("AppUser not found with id: " + itemDTO.getAppUserId()));
        item.setAppUser(appUser);

        return item;
    }

}
