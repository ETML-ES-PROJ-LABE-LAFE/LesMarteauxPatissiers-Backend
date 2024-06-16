package ch.etmles.auction.Exceptions;


public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(Long id) {
        super("Item not found with id : " + id);
    }
}

