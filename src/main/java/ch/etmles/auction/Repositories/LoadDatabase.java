package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final AppUserRepository appUserRepository;
    private final AuctionRepository auctionRepository;
    private final BidRepository bidRepository;

    public LoadDatabase(AppUserRepository appUserRepository, AuctionRepository auctionRepository, BidRepository bidRepository) {
        this.appUserRepository = appUserRepository;
        this.auctionRepository = auctionRepository;
        this.bidRepository = bidRepository;
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
            //log.info("List of sub-categories: " + subCat);

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
        AppUser user1 = new AppUser("Toto","Giovanni",BigDecimal.valueOf(8000));
        appUserRepository.save(user);
        appUserRepository.save(user1);
        Item item1 = new Item("Iphone 12", subCat.get(0),user, "Latest Apple smartphone","Smartphone1.png", BigDecimal.valueOf(999.99));
        Item item2 = new Item("Iphone 13", subCat.get(0),user1, "Latest Apple smartphone","Smartphone2.png", BigDecimal.valueOf(1099.99));
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(new Item("Iphone 13", subCat.get(0),user1, "Latest Apple smartphone","Smartphone3.png", BigDecimal.valueOf(1299.99)));
        itemRepository.save(new Item("Iphone 19 Pro Plus", subCat.get(0),user, "Latest Apple smartphone","Smartphone4.png", BigDecimal.valueOf(2999.99)));
        itemRepository.save(new Item("MacBook Pro 13' pouces", subCat.get(1),user, "High-end Apple laptop","Computer1.png", BigDecimal.valueOf(1999.99)));

        itemRepository.save(new Item("MacBook Pro 15' pouces", subCat.get(1),user1, "High-end Apple laptop","Computer2.png", BigDecimal.valueOf(2499.99)));
        itemRepository.save(new Item("MacBook Pro 17' pouces", subCat.get(1),user, "High-end Apple laptop","Computer3.png", BigDecimal.valueOf(2999.99)));
        itemRepository.save(new Item("Nikon D3500", subCat.get(2),user1, "Entry-level DSLR camera","Camera1.png", BigDecimal.valueOf(499.99)));
        itemRepository.save(new Item("Nikon D6500", subCat.get(2),user, "Entry-level DSLR camera","Camera2.png", BigDecimal.valueOf(499.99)));
        itemRepository.save(new Item("Samsung TV 55\"", subCat.get(3),user, "4K UHD Smart TV","Television1.png", BigDecimal.valueOf(799.99)));
        itemRepository.save(new Item("USB-C Adapter", subCat.get(4),user1, "Multiport adapter","USB-C-Adaptater.png", BigDecimal.valueOf(29.99)));

        itemRepository.save(new Item("Ergonomic Chair", subCat.get(5),user1, "Comfortable office chair",null, BigDecimal.valueOf(149.99)));
        itemRepository.save(new Item("Wooden Table", subCat.get(6),user, "Solid wood dining table",null ,BigDecimal.valueOf(299.99)));
      /*  itemRepository.save(new Item("Leather Sofa", subCat.get(7),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Luxurious leather sofa", BigDecimal.valueOf(899.99)));
        itemRepository.save(new Item("King Size Bed", subCat.get(8),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Comfortable king size bed", BigDecimal.valueOf(499.99)));
        itemRepository.save(new Item("Metal Shelf", subCat.get(9),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Durable metal storage shelf", BigDecimal.valueOf(79.99)));
*/
        itemRepository.save(new Item("Men's T-Shirt", subCat.get(10),user, "Comfortable cotton t-shirt",null, BigDecimal.valueOf(19.99)));
        itemRepository.save(new Item("Women's Dress", subCat.get(11),user1, "Elegant evening dress", null, BigDecimal.valueOf(99.99)));
     /*   itemRepository.save(new Item("Running Shoes", subCat.get(12),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Lightweight running shoes", BigDecimal.valueOf(59.99)));
        itemRepository.save(new Item("Gold Necklace", subCat.get(13),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "18k gold necklace", BigDecimal.valueOf(499.99)));
        itemRepository.save(new Item("Leather Handbag", subCat.get(14),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Stylish leather handbag", BigDecimal.valueOf(199.99)));
*/
        itemRepository.save(new Item("Harry Potter Book Set", subCat.get(15),user1, "Complete book set",null, BigDecimal.valueOf(89.99)));
        itemRepository.save(new Item("Inception Blu-ray", subCat.get(16),user, "Science fiction movie",null, BigDecimal.valueOf(14.99)));
    /*    itemRepository.save(new Item("Guitar", subCat.get(17),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Acoustic guitar", BigDecimal.valueOf(129.99)));
        itemRepository.save(new Item("Spider-Man Comics", subCat.get(18),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Marvel comic series", BigDecimal.valueOf(29.99)));
        itemRepository.save(new Item("National Geographic Magazine", subCat.get(19),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Monthly subscription", BigDecimal.valueOf(19.99)));
*/
        itemRepository.save(new Item("Starry Night Painting", subCat.get(20),user,"Famous painting by Van Gogh",null, BigDecimal.valueOf(499.99)));
        itemRepository.save(new Item("Bronze Sculpture", subCat.get(21),user1, "Abstract bronze sculpture",null, BigDecimal.valueOf(299.99)));
    /*    itemRepository.save(new Item("Victorian Chair", subCat.get(22),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Antique Victorian chair", BigDecimal.valueOf(399.99)));
        itemRepository.save(new Item("Baseball Card Collection", subCat.get(23),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Vintage baseball cards", BigDecimal.valueOf(599.99)));
        itemRepository.save(new Item("Black and White Photography", subCat.get(24),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Artistic photograph", BigDecimal.valueOf(149.99)));
*/
        itemRepository.save(new Item("Yoga Mat", subCat.get(25),user, "Non-slip yoga mat",null, BigDecimal.valueOf(29.99)));
   /*     itemRepository.save(new Item("Basketball Jersey", subCat.get(26),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Official NBA jersey", BigDecimal.valueOf(89.99)));
        itemRepository.save(new Item("Camping Tent", subCat.get(27),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "4-person camping tent", BigDecimal.valueOf(149.99)));
        itemRepository.save(new Item("Action Figure", subCat.get(28),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "Superhero action figure", BigDecimal.valueOf(39.99)));
        itemRepository.save(new Item("Fishing Rod", subCat.get(29),new AppUser("Jean-Mich","Philippiano",BigDecimal.valueOf(1000)), "High-quality fishing rod", BigDecimal.valueOf(79.99)));

        log.info(itemRepository.findById(1L).toString());

        int nbItems = itemRepository.findAll().size();
        for (int i = nbItems; i < 70; i++) {
            itemRepository.save(new Item("Item " + (i+1), subCat.get(i % subCat.size()), "Description for item " + (i+1), BigDecimal.valueOf((i+1) * 10.0)));
        }*/
        Auction auction1 = new Auction(item1, LocalDateTime.now(),LocalDateTime.from(LocalDateTime.now()).plusDays(5));
        Auction auction2 = new Auction(item2, LocalDateTime.now(),LocalDateTime.from(LocalDateTime.now()).plusDays(5));
        auctionRepository.save(auction1);
        auctionRepository.save(auction2);
        Bid bid1 = new Bid(item1,user, auction1,BigDecimal.valueOf(1000),LocalDateTime.now());
        Bid bid2 = new Bid(item2,user1, auction2,BigDecimal.valueOf(1000),LocalDateTime.now());
        bidRepository.save(bid1);
        bidRepository.save(bid2);

    }

}
