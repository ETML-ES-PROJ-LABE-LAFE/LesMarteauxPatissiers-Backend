package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
}

