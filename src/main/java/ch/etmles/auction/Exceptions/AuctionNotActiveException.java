package ch.etmles.auction.Exceptions;

public class AuctionNotActiveException extends RuntimeException {
    public AuctionNotActiveException(long id) {
        super("Auction is not active. ID: " + id);
    }
}

