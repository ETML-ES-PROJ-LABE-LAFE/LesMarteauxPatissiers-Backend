package ch.etmles.auction.Repositories;

import ch.etmles.auction.Entities.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {

        @EntityGraph(attributePaths = "subCategories")
        Optional<Category> findById(Long id);

        @Query("SELECT c FROM Category c LEFT JOIN FETCH c.subCategories")
        List<Category> findAllWithSubCategories();
}
