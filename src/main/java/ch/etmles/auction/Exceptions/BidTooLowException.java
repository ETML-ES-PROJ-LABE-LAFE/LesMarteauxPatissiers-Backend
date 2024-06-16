package ch.etmles.auction.Exceptions;

import java.math.BigDecimal;

public class BidTooLowException extends RuntimeException {
    public BidTooLowException(BigDecimal amount) {
        super("Your bid is too low. Your bid amount must be greater than " + amount + " CHF");
    }
}
