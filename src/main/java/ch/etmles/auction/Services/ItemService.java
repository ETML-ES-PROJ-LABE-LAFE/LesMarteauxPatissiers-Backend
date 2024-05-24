package ch.etmles.auction.Services;

import ch.etmles.auction.DTOs.ItemDTO;
import ch.etmles.auction.Entities.Item;
import ch.etmles.auction.Exceptions.ItemNotFoundException;
import ch.etmles.auction.Mappers.ItemMapper;
import ch.etmles.auction.Repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemMapper itemMapper;

    public List<ItemDTO> getAllItems() {
        List<Item> items = itemRepository.findAll();
        return items.stream().map(itemMapper::convertToDTO).collect(Collectors.toList());
    }

    public ItemDTO getItemById(long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        return itemMapper.convertToDTO(item);
    }

    public ItemDTO createItem(ItemDTO itemDTO) {
        Item item = itemMapper.convertToEntity(itemDTO);
        Item savedItem = itemRepository.save(item);
        return itemMapper.convertToDTO(savedItem);
    }

    public ItemDTO updateOrCreateItem(long id, ItemDTO itemDTO) {
        Item item = itemRepository.findById(id).orElseGet(() -> new Item());
        item.setId(id);
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());
        item.setInitialPrice(itemDTO.getInitialPrice());
        item.setLastBid(itemDTO.getLastBid());
        item.setCategory(itemMapper.convertToEntity(itemDTO).getCategory());
        Item savedItem = itemRepository.save(item);
        return itemMapper.convertToDTO(savedItem);
    }

    public void deleteItem(long id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
        itemRepository.delete(item);
    }

    public boolean existsById(long id) {
        return itemRepository.existsById(id);
    }

    public List<ItemDTO> getItemsBySubCategoryId(long subCategoryId) {
        List<Item> items = itemRepository.findByCategory_Id(subCategoryId);
        return items.stream().map(itemMapper::convertToDTO).collect(Collectors.toList());
    }
}
