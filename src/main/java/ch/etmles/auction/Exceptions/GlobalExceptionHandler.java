package ch.etmles.auction.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;


@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String itemNotFoundHandler(ItemNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String categoryNotFoundHandler(CategoryNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AuctionNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String auctionNotFoundHandler(AuctionNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AuctionNotActiveException.class)
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    String auctionNotActiveHandler(AuctionNotActiveException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AuctionAlreadyActiveException.class)
    @ResponseStatus(HttpStatus.NOT_MODIFIED)
    String auctionAlreadyActiveHandler(AuctionAlreadyActiveException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(BidTooLowException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String bidTooLowHandler(BidTooLowException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AppUserNotFoundException.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    String appUserNotFoundHandler(AppUserNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(InsufficientCreditException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String insufficientCreditHandler(InsufficientCreditException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ItemMismatchException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String itemMismatchHandler(ItemMismatchException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

