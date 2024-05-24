package ch.etmles.auction.Mappers;

import ch.etmles.auction.DTOs.ItemDTO;
import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Entities.Item;
import ch.etmles.auction.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    @Autowired
    private CategoryService categoryService;

    public ItemDTO convertToDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setReference(item.getReference());
        itemDTO.setName(item.getName());
        itemDTO.setCategoryId(item.getCategory().getId());
        itemDTO.setDescription(item.getDescription());
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
        item.setInitialPrice(itemDTO.getInitialPrice());
        item.setLastBid(itemDTO.getLastBid());
        Category category = categoryService.getCategoryById(itemDTO.getCategoryId());
        item.setCategory(category);
        return item;
    }
}
