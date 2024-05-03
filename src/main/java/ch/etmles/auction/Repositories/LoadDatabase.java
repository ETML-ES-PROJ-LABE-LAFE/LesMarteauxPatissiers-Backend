package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Category;
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
    CommandLineRunner initDatabase(ItemRepository repository){
        return args->{
            log.info("Preloading " + repository.save(new Item("Bureau Ikea", Category.MOBILIER, "Bureau assis debout pour un max de productivité", BigDecimal.valueOf(12.5))));
            log.info("Preloading " + repository.save(new Item("La Joconde",Category.ART, "Le fameux tableau de Léonard de Vinci", BigDecimal.valueOf(12522345.5))));
        };
    }
}
