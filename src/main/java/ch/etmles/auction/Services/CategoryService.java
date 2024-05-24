package ch.etmles.auction.Services;

import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Exceptions.CategoryNotFoundException;
import ch.etmles.auction.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateCategory(Long id, Category categoryDetails) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        category.setName(categoryDetails.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepository.delete(category);
    }

    public List<Category> getSubCategories(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return category.getSubCategories();
    }

    public Category addSubCategory(Long id, Category subCategory) {
        Category parentCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        parentCategory.addSubCategory(subCategory);
        categoryRepository.save(subCategory); // Save the new subcategory
        categoryRepository.save(parentCategory); // Update the parent category
        return subCategory;
    }

    public Category getSubCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public void deleteSubCategory(Long subId) {
        Category subCategory = categoryRepository.findById(subId)
                .orElseThrow(() -> new CategoryNotFoundException(subId));
        Category parentCategory = subCategory.getParentCategory();
        if (parentCategory != null) {
            parentCategory.removeSubCategory(subCategory);
            categoryRepository.save(parentCategory); // Update the parent category
        }
        categoryRepository.delete(subCategory);
    }
}
