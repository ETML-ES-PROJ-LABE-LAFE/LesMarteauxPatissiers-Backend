package ch.etmles.auction.Mappers;

import ch.etmles.auction.DTOs.CategoryDTO;
import ch.etmles.auction.Entities.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryMapper {

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

    public Category convertToEntity(CategoryDTO categoryDTO, Category parentCategory) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setParentCategory(parentCategory);
        return category;
    }
}
