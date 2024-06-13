package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Auction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findByItemId(long id);

    @Query("SELECT a FROM Auction a WHERE a.item.id = :itemId AND a.isActive = true")
    List<Auction> findActiveAuctionsByItemId(long itemId);

}
