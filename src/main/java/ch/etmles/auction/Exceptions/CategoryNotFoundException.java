package ch.etmles.auction.Exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("Impossible de trouver la catégorie n°" + id);
    }
}
