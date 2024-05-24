package ch.etmles.auction.Controllers;

import ch.etmles.auction.DTOs.CategoryDTO;
import ch.etmles.auction.DTOs.ItemDTO;
import ch.etmles.auction.Entities.Category;
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
        return ResponseEntity.ok(categoryMapper.convertToDTO(category));
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category = categoryMapper.convertToEntity(categoryDTO);
        Category savedCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(categoryMapper.convertToDTO(savedCategory));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable Long id, @RequestBody CategoryDTO categoryDTO) {
        Category categoryDetails = categoryMapper.convertToEntity(categoryDTO);
        Category updatedCategory = categoryService.updateCategory(id, categoryDetails);
        return ResponseEntity.ok(categoryMapper.convertToDTO(updatedCategory));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/subcategories")
    public ResponseEntity<List<CategoryDTO>> getSubCategories(@PathVariable Long id) {
        List<Category> subCategories = categoryService.getSubCategories(id);
        List<CategoryDTO> subCategoryDTOs = subCategories.stream()
                .map(categoryMapper::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(subCategoryDTOs);
    }

    @PostMapping("/{id}/subcategories")
    public ResponseEntity<CategoryDTO> addSubCategory(@PathVariable Long id, @RequestBody CategoryDTO subCategoryDTO) {
        Category subCategory = categoryMapper.convertToEntity(subCategoryDTO);
        Category savedSubCategory = categoryService.addSubCategory(id, subCategory);
        return ResponseEntity.ok(categoryMapper.convertToDTO(savedSubCategory));
    }

    @DeleteMapping("/subcategories/{subId}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long subId) {
        categoryService.deleteSubCategory(subId);
        return ResponseEntity.ok().build();
    }

    //récupération des items par catégorie
    @GetMapping("/{id}/items")
    public ResponseEntity<List<ItemDTO>> getItemsByCategoryId(@PathVariable Long id) {
        List<ItemDTO> items = itemService.getItemsBySubCategoryId(id);
        return ResponseEntity.ok(items);
    }
}
