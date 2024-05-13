package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Categorie;
import ch.etmles.auction.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    //TODO : modifier selon nouvelle class categorie
    List<Item> findByCategorie(Categorie categorie);


}

