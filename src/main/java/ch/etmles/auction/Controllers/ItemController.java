package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Item;
import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Repositories.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    /* curl sample :
    curl -i localhost:8080/items
    curl -i localhost:8080/items?category=ART
    */
    @GetMapping
    List<Item> getAllItems(@RequestParam(required = false) Category category) {
        if (category != null) {
            return itemRepository.findByCategory(category);
        }
        return itemRepository.findAll();
    }

    /* curl sample :
    curl -i localhost:8080/items/1
    */
    @GetMapping("/{id}")
    Item getItemById(@PathVariable long id) {
        return itemRepository.findById(id).orElseThrow(()-> new ItemNotFoundException(id));
    }

    /*
    curl -i -X POST localhost:8080/items ^
            -H "Content-Type: application/json" ^
            -d "{\"name\": \"Chaise Moderne\",\"category\": \"MOBILIER\", \"description\": \"Une chaise moderne en métal\", \"initial_price\": 75.0, \"last_bid\": 75.0, \"active\":true}"
    */
    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item savedItem = itemRepository.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    /*
    curl -i -X PUT localhost:8080/items/3 ^
            -H "Content-Type: application/json" ^
            -d "{\"name\": \"Chaise Ancienne\",\"category\": \"ART\", \"description\": \"Une chaise ancienne en bois massif et velour\", \"initial_price\": 150.0, \"last_bid\": 150.0}"
    */
   //@PutMapping("/{id}")
   //public ResponseEntity<Item> updateItem(@PathVariable long id, @RequestBody Item newItem) {
   //    return itemRepository.findById(id).map(item -> {
   //        item.setName(newItem.getName());
   //        item.setDescription(newItem.getDescription());
   //        item.setInitial_price(newItem.getInitial_price());
   //        item.setLast_bid(newItem.getLast_bid());
   //        return ResponseEntity.ok(itemRepository.save(item));
   //    }).orElseThrow(() -> new ItemNotFoundException(id));
   //}


    @PutMapping("/{id}")
    public ResponseEntity<Item> updateOrCreateItem(@PathVariable long id, @RequestBody Item newItem) {
        boolean isCreation = !itemRepository.existsById(id);
        Item savedItem = itemRepository.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setDescription(newItem.getDescription());
                    item.setInitial_price(newItem.getInitial_price());
                    item.setLast_bid(newItem.getLast_bid());
                    item.setCategory(newItem.getCategory());
                    return itemRepository.save(item);
                }).orElseGet(() -> {
                    newItem.setId(id);
                    return itemRepository.save(newItem);
                });
        if (isCreation) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedItem); // 201 pour la création
        } else {
            return ResponseEntity.ok(savedItem); // 200 pour la mise à jour
        }
    }

    /* curl sample :
    curl -i -X DELETE localhost:8080/items/2
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable long id) {
        if (!itemRepository.existsById(id)) {
            throw new ItemNotFoundException(id);
        }
        itemRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
