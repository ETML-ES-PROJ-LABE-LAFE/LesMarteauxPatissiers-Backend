package ch.etmles.auction.Exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long id) {
        super("Unable to find Category. ID : " + id);
    }
}

