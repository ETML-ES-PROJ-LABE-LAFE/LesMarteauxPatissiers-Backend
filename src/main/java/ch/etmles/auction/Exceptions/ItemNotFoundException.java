package ch.etmles.auction.Exceptions;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long id) {
        super("Item n°" + id + " n'existe pas");
    }
}
