package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Auction;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findByItemId(long id);
}
