package ch.etmles.auction.Repositories;
import ch.etmles.auction.Entities.Categorie;
import ch.etmles.auction.Entities.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ItemRepository itemRepository, CategorieRepository categorieRepository){
        return args -> {

            Categorie bureauCat = categorieRepository.save(new Categorie("Bureau"));
            Categorie artCat = categorieRepository.save(new Categorie("Art"));

            log.info("Cat1 :" + bureauCat);
            log.info("Cat2 :" + artCat);

            log.info("Preloading " + itemRepository.save(new Item("Bureau Ikea", bureauCat, "Bureau assis debout pour un max de productivité", BigDecimal.valueOf(12.5))));
            log.info("Preloading " + itemRepository.save(new Item("La Joconde", artCat, "Le fameux tableau de Léonard de Vinci", BigDecimal.valueOf(12522345.5))));
        };
    }
}

