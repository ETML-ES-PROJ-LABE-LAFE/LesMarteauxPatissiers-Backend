package ch.etmles.auction.Exceptions;

public class BidNotAllowedException extends RuntimeException {
    public BidNotAllowedException() {super("You cannot create a bid on your own auction Michel !");}
}
