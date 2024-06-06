package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {


}
