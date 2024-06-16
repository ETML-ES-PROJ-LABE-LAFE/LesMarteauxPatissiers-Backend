package ch.etmles.auction.Exceptions;

import java.math.BigDecimal;

public class InsufficientCreditException extends RuntimeException {
    public InsufficientCreditException(Long id, BigDecimal credit) {
        super("User with id " + id + " does not have enough credit. Available credit : " + credit);
    }
}
