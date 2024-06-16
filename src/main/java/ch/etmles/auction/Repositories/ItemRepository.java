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

    @Query("SELECT i FROM Item i WHERE i.id IN " +
            "(SELECT b.item.id FROM Bid b " +
            "WHERE b.appUser.id = :userId " +
            "AND b.id IN (SELECT MAX(b2.id) FROM Bid b2 " +
            "WHERE b2.auction.id = b.auction.id AND b2.auction.isActive = false))")
    List<Item> findItemsWonByUser(long userId);

    @Query("SELECT i FROM Item i WHERE i.appUser.id = :userId AND i.id IN " +
            "(SELECT b.item.id FROM Bid b " +
            "WHERE b.id IN (SELECT MAX(b2.id) FROM Bid b2 WHERE b2.auction.id = b.auction.id AND b2.auction.isActive = false))")
    List<Item> findItemsSoldByUser(long userId);
}

