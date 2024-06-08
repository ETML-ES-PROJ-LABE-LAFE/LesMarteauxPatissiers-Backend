package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Auction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuctionRepository extends JpaRepository<Auction, Long> {

}
