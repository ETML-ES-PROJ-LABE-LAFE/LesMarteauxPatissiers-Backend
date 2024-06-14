package ch.etmles.auction.Controllers;

import ch.etmles.auction.DTOs.CategoryDTO;
import ch.etmles.auction.DTOs.ItemDTO;
import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Exceptions.CategoryNotFoundException;
import ch.etmles.auction.Mappers.CategoryMapper;
import ch.etmles.auction.Services.CategoryService;
import ch.etmles.auction.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryMapper categoryMapper;


    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();
        return categories.stream().map(categoryMapper::convertToDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            throw new CategoryNotFoundException(id);
        }
        return ResponseEntity.ok(categoryMapper.convertToDTO(category)) ;
    }

    @GetMapping("/{id}/subcategories")
    public ResponseEntity<List<CategoryDTO>> getSubCategories(@PathVariable Long id) {
        List<Category> subCategories = categoryService.getSubCategories(id);
        List<CategoryDTO> subCategoryDTOs = subCategories.stream()
                .map(categoryMapper::convertToDTO)
                .collect(Collectors.toList());
        return !subCategories.isEmpty() ? ResponseEntity.ok(subCategoryDTOs) : ResponseEntity.notFound().build();
    }

    //récupération des items par catégorie
    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemDTO>> getItemsByCategoryId(@PathVariable Long id) {
        List<ItemDTO> items = itemService.getItemsBySubCategoryId(id);
        return !items.isEmpty() ? ResponseEntity.ok(items) : ResponseEntity.notFound().build();
    }
}
