package ch.etmles.auction.Mappers;

import ch.etmles.auction.DTOs.CategoryDTO;
import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

    @Autowired
    private CategoryService categoryService;

    public CategoryDTO convertToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        if (category.getParentCategory() != null) {
            categoryDTO.setParentCategoryId(category.getParentCategory().getId());
        }
        List<Long> subCategoryIds = category.getSubCategories().stream()
                .map(Category::getId)
                .collect(Collectors.toList());
        categoryDTO.setSubCategoryIds(subCategoryIds);
        return categoryDTO;
    }

    public Category convertToEntity(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        if (categoryDTO.getParentCategoryId() != null) {
            category.setParentCategory(categoryService.getCategoryById(categoryDTO.getParentCategoryId()));
        }
        return category;
    }
}
