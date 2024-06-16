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

    public List<Category> getSubCategories(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        return category.getSubCategories();
    }

}
