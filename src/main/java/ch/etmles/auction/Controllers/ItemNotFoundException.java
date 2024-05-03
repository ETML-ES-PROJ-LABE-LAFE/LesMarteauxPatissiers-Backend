package ch.etmles.auction.Controllers;

public class ItemNotFoundException extends RuntimeException {
    ItemNotFoundException(Long id) {
        super("Item n°" + id + " n'existe pas");
    }
}
