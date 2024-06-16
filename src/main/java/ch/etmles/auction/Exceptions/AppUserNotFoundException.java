package ch.etmles.auction.Exceptions;

public class AppUserNotFoundException extends RuntimeException {
    public AppUserNotFoundException(Long id) {
        super("AppUser not found with id : " + id);
    }
}

