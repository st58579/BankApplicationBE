package cz.upce.bank.eb.service;

/**
 * Výjimka při provádění logiky aplikace
 */

public class ServiceException extends RuntimeException {

    private String message;

    public ServiceException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
