package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Exceptions.CategoryNotFoundException;
import ch.etmles.auction.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Routes pour les catégories principales
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        category.setName(categoryDetails.getName());
        Category updatedCategory = categoryRepository.save(category);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepository.delete(category);
        return ResponseEntity.ok().build();
    }

    // Routes pour les sous-catégories
    @GetMapping("/{id}/subcategories")
    public ResponseEntity<List<Category>> getSubCategories(@PathVariable Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return ResponseEntity.ok(category.getSubCategories());
    }

    @PostMapping("/{id}/subcategories")
    public ResponseEntity<Category> addSubCategory(@PathVariable Long id, @RequestBody Category subCategory) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        category.addSubCategory(subCategory);
        categoryRepository.save(subCategory); // Save the new subcategory
        categoryRepository.save(category); // Update the parent category
        return ResponseEntity.ok(subCategory);
    }

    @GetMapping("/subcategories/{id}")
    public ResponseEntity<Category> getSubCategoriesById(@PathVariable Long id) {
        Category subCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return ResponseEntity.ok(subCategory);
    }

    @DeleteMapping("/subcategories/{subId}")
    public ResponseEntity<Void> deleteSubCategory(@PathVariable Long subId) {
        Category subCategory = categoryRepository.findById(subId)
                .orElseThrow(() -> new CategoryNotFoundException(subId));
        Category parentCategory = subCategory.getParentCategory();
        if (parentCategory != null) {
            parentCategory.removeSubCategory(subCategory);
            categoryRepository.save(parentCategory); // Update the parent category
        }
        categoryRepository.delete(subCategory);
        return ResponseEntity.ok().build();
    }
}
