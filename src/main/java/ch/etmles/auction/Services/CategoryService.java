package ch.etmles.auction.Services;

import ch.etmles.auction.DTOs.CategoryDTO;
import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Exceptions.CategoryNotFoundException;
import ch.etmles.auction.Mappers.CategoryMapper;
import ch.etmles.auction.Repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::convertToDTO).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return categoryMapper.convertToDTO(category);
    }

    public List<CategoryDTO> getSubCategories(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        List<Category> subCategories = category.getSubCategories();
        return subCategories.stream()
                .map(categoryMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
