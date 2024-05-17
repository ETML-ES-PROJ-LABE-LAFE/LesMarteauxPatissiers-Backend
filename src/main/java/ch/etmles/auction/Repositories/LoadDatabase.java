package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Entities.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    @Transactional
    CommandLineRunner initDatabase(ItemRepository itemRepository, CategoryRepository categoryRepository) {
        return args -> {

            // Creating and saving parent categories
            Category electronics = new Category("Electronics");
            Category furniture = new Category("Furniture");
            Category clothing = new Category("Clothing and Accessories");
            Category booksMoviesMusic = new Category("Books, Movies, and Music");
            Category artAntiques = new Category("Art and Antiques");
            Category sportsLeisure = new Category("Sports and Leisure");

            categoryRepository.save(electronics);
            categoryRepository.save(furniture);
            categoryRepository.save(clothing);
            categoryRepository.save(booksMoviesMusic);
            categoryRepository.save(artAntiques);
            categoryRepository.save(sportsLeisure);

            // Creating and assigning sub-categories
            createAndSaveSubCategories(categoryRepository, electronics, "Smartphones", "Computers", "Cameras", "Televisions", "Accessories");
            createAndSaveSubCategories(categoryRepository, furniture, "Chairs", "Tables", "Sofas", "Beds", "Storage");
            createAndSaveSubCategories(categoryRepository, clothing, "Men's Clothing", "Women's Clothing", "Shoes", "Jewelry", "Handbags");
            createAndSaveSubCategories(categoryRepository, booksMoviesMusic, "Books", "Movies", "Music", "Comics", "Magazines");
            createAndSaveSubCategories(categoryRepository, artAntiques, "Paintings", "Sculptures", "Antique Furniture", "Collectibles", "Photography");
            createAndSaveSubCategories(categoryRepository, sportsLeisure, "Sport Equipment", "Sport Clothing", "Camping Gear", "Toys", "Fishing Gear");

            // Example items
            Category tableCat = categoryRepository.findByName("Tables").orElseThrow();
            Category paintingCat = categoryRepository.findByName("Paintings").orElseThrow();

            log.info("Preloading " + itemRepository.save(new Item("Bureau Ikea", tableCat, "Bureau assis debout pour un max de productivité", BigDecimal.valueOf(12.5))));
            log.info("Preloading " + itemRepository.save(new Item("La Joconde", paintingCat, "Le fameux tableau de Léonard de Vinci", BigDecimal.valueOf(12522345.5))));
        };
    }

    private void createAndSaveSubCategories(CategoryRepository categoryRepository, Category parentCategory, String... subCategoryNames) {
        for (String subCategoryName : subCategoryNames) {
            Category subCategory = new Category(subCategoryName);
            parentCategory.addSubCategory(subCategory);
            categoryRepository.save(subCategory);
            log.info("Preloading sub-category: " + subCategoryName + " to parent category: " + parentCategory.getName());
        }
        categoryRepository.save(parentCategory); // Update parent with sub-categories
    }
}
