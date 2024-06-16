package ch.etmles.auction.Exceptions;

public class AuctionAlreadyActiveException extends RuntimeException {
    public AuctionAlreadyActiveException(Long id) {
        super("An auction for this item is already active with id : " + id);
    }
}
