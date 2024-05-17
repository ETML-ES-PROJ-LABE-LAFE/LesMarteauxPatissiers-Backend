package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

        @EntityGraph(attributePaths = "subCategories")
        Optional<Category> findById(Long id);

        Optional<Category> findByName(String name);
}
