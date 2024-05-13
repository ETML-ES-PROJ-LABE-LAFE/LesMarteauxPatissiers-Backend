package ch.etmles.auction.Controllers;

import ch.etmles.auction.Entities.Categorie;
import ch.etmles.auction.Repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:8081")
public class CategorieController {

    @Autowired
    private CategorieRepository categorieRepository;

    // Récupérer toutes les catégories
    @GetMapping
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    // Récupérer une catégorie par son ID
    @GetMapping("/{id}")
    public ResponseEntity<Categorie> getCategoryById(@PathVariable long id) {
        Optional<Categorie> categorie = categorieRepository.findById(id);
        return categorie.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Ajouter une nouvelle catégorie
    @PostMapping
    public Categorie createCategory(@RequestBody Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    // Mettre à jour une catégorie
    @PutMapping("/{id}")
    public ResponseEntity<Categorie> updateCategory(@PathVariable long id, @RequestBody Categorie categorieDetails) {
        return categorieRepository.findById(id).map(categorie -> {
            categorie.setNom(categorieDetails.getNom());
            categorie.setCategorieParent(categorieDetails.getCategorieParent());
            Categorie updatedCategorie = categorieRepository.save(categorie);
            return ResponseEntity.ok(updatedCategorie);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Supprimer une catégorie
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable long id) {
        return categorieRepository.findById(id).map(categorie -> {
            categorieRepository.delete(categorie);
            return ResponseEntity.ok().build();
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
