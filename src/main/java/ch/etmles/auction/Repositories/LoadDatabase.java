package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.AppUser;
import ch.etmles.auction.Entities.Category;
import ch.etmles.auction.Entities.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final AppUserRepository appUserRepository;

    public LoadDatabase(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Bean
    @Transactional
    CommandLineRunner initDatabase(ItemRepository itemRepository, CategoryRepository categoryRepository, AppUserRepository appUserRepository) {
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



            // Fetching all sub-categories
            List<Category> subCat = new ArrayList<>();
            List<Category> cat = categoryRepository.findAllWithSubCategories();
            for (Category c : cat) {
                subCat.addAll(c.getSubCategories());
            }
            log.info("List of sub-categories: " + subCat);

            // Adding items to the sub-categories
            addItemsToSubCategories(itemRepository, subCat);

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

    private void addItemsToSubCategories(ItemRepository itemRepository, List<Category> subCat) {
        AppUser user = new AppUser("Toto","Philippe",BigDecimal.valueOf(1000));
        appUserRepository.save(user);

        itemRepository.save(new Item("Iphone 12", subCat.get(0),user, "Latest Apple smartphone", BigDecimal.valueOf(999.99)));
        itemRepository.save(new Item("Iphone 13", subCat.get(0),user, "Latest Apple smartphone", BigDecimal.valueOf(1099.99)));
        itemRepository.save(new Item("Iphone 13", subCat.get(0),user, "Latest Apple smartphone", BigDecimal.valueOf(1299.99)));
        itemRepository.save(new Item("Iphone 19 Pro Plus", subCat.get(0),user, "Latest Apple smartphone", BigDecimal.valueOf(2999.99)));
        itemRepository.save(new Item("MacBook Pro 13' pouces", subCat.get(1),user, "High-end Apple laptop", BigDecimal.valueOf(1999.99)));
        /*
        itemRepository.save(new Item("MacBook Pro 15' pouces", subCat.get(1),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "High-end Apple laptop", BigDecimal.valueOf(2499.99)));
        itemRepository.save(new Item("MacBook Pro 17' pouces", subCat.get(1),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "High-end Apple laptop", BigDecimal.valueOf(2999.99)));
        itemRepository.save(new Item("Nikon D3500", subCat.get(2),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Entry-level DSLR camera", BigDecimal.valueOf(499.99)));
        itemRepository.save(new Item("Nikon D6500", subCat.get(2),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Entry-level DSLR camera", BigDecimal.valueOf(499.99)));
        itemRepository.save(new Item("Samsung TV 55\"", subCat.get(3),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "4K UHD Smart TV", BigDecimal.valueOf(799.99)));
        itemRepository.save(new Item("USB-C Adapter", subCat.get(4),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Multiport adapter", BigDecimal.valueOf(29.99)));

        itemRepository.save(new Item("Ergonomic Chair", subCat.get(5),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Comfortable office chair", BigDecimal.valueOf(149.99)));
        itemRepository.save(new Item("Wooden Table", subCat.get(6),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Solid wood dining table", BigDecimal.valueOf(299.99)));
        itemRepository.save(new Item("Leather Sofa", subCat.get(7),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Luxurious leather sofa", BigDecimal.valueOf(899.99)));
        itemRepository.save(new Item("King Size Bed", subCat.get(8),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Comfortable king size bed", BigDecimal.valueOf(499.99)));
        itemRepository.save(new Item("Metal Shelf", subCat.get(9),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Durable metal storage shelf", BigDecimal.valueOf(79.99)));

        itemRepository.save(new Item("Men's T-Shirt", subCat.get(10),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Comfortable cotton t-shirt", BigDecimal.valueOf(19.99)));
        itemRepository.save(new Item("Women's Dress", subCat.get(11),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Elegant evening dress", BigDecimal.valueOf(99.99)));
        itemRepository.save(new Item("Running Shoes", subCat.get(12),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Lightweight running shoes", BigDecimal.valueOf(59.99)));
        itemRepository.save(new Item("Gold Necklace", subCat.get(13),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "18k gold necklace", BigDecimal.valueOf(499.99)));
        itemRepository.save(new Item("Leather Handbag", subCat.get(14),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Stylish leather handbag", BigDecimal.valueOf(199.99)));

        itemRepository.save(new Item("Harry Potter Book Set", subCat.get(15),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Complete book set", BigDecimal.valueOf(89.99)));
        itemRepository.save(new Item("Inception Blu-ray", subCat.get(16),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Science fiction movie", BigDecimal.valueOf(14.99)));
        itemRepository.save(new Item("Guitar", subCat.get(17),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Acoustic guitar", BigDecimal.valueOf(129.99)));
        itemRepository.save(new Item("Spider-Man Comics", subCat.get(18),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Marvel comic series", BigDecimal.valueOf(29.99)));
        itemRepository.save(new Item("National Geographic Magazine", subCat.get(19),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Monthly subscription", BigDecimal.valueOf(19.99)));

        itemRepository.save(new Item("Starry Night Painting", subCat.get(20),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Famous painting by Van Gogh", BigDecimal.valueOf(499.99)));
        itemRepository.save(new Item("Bronze Sculpture", subCat.get(21),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Abstract bronze sculpture", BigDecimal.valueOf(299.99)));
        itemRepository.save(new Item("Victorian Chair", subCat.get(22),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Antique Victorian chair", BigDecimal.valueOf(399.99)));
        itemRepository.save(new Item("Baseball Card Collection", subCat.get(23),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Vintage baseball cards", BigDecimal.valueOf(599.99)));
        itemRepository.save(new Item("Black and White Photography", subCat.get(24),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Artistic photograph", BigDecimal.valueOf(149.99)));

        itemRepository.save(new Item("Yoga Mat", subCat.get(25),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Non-slip yoga mat", BigDecimal.valueOf(29.99)));
        itemRepository.save(new Item("Basketball Jersey", subCat.get(26),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Official NBA jersey", BigDecimal.valueOf(89.99)));
        itemRepository.save(new Item("Camping Tent", subCat.get(27),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "4-person camping tent", BigDecimal.valueOf(149.99)));
        itemRepository.save(new Item("Action Figure", subCat.get(28),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Superhero action figure", BigDecimal.valueOf(39.99)));
        itemRepository.save(new Item("Fishing Rod", subCat.get(29),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "High-quality fishing rod", BigDecimal.valueOf(79.99)));

        log.info(itemRepository.findById(1L).toString());

        int nbItems = itemRepository.findAll().size();
        for (int i = nbItems; i < 70; i++) {
            itemRepository.save(new Item("Item " + (i+1), subCat.get(i % subCat.size()), "Description for item " + (i+1), BigDecimal.valueOf((i+1) * 10.0)));
        }*/
    }
}
