package ch.etmles.auction.Exceptions;

public class ItemMismatchException extends RuntimeException {
    public ItemMismatchException(long id) {
        super("Wrong Item. ID : " + id);
    }
}
