package ch.etmles.auction.Controllers;

import ch.etmles.auction.DTOs.CategoryDTO;
import ch.etmles.auction.DTOs.ItemDTO;
import ch.etmles.auction.Services.CategoryService;
import ch.etmles.auction.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/{id}/subcategories")
    public ResponseEntity<List<CategoryDTO>> getSubCategories(@PathVariable Long id) {
        List<CategoryDTO> subCategoryDTOs = categoryService.getSubCategories(id);
        return !subCategoryDTOs.isEmpty() ? ResponseEntity.ok(subCategoryDTOs) : ResponseEntity.noContent().build();
    }

    // Récupération des items par catégorie
    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemDTO>> getItemsByCategoryId(@PathVariable Long id) {
        List<ItemDTO> items = itemService.getItemsBySubCategoryId(id);
        return !items.isEmpty() ? ResponseEntity.ok(items) : ResponseEntity.noContent().build();
    }
}
