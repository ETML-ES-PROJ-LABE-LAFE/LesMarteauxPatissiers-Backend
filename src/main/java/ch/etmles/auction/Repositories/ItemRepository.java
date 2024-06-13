package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    List<Item> findByCategory_Id(long categoryId);

    @Query("SELECT i FROM Item i WHERE i.id IN (SELECT b.item.id FROM Bid b WHERE b.appUser.id = :buyerId)")
    List<Item> findItemsByBuyer(long buyerId);

    @Query("SELECT i FROM Item i WHERE i.appUser.id = :sellerId")
    List<Item> findItemsBySeller(long sellerId);
}

