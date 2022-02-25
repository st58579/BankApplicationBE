package cz.upce.bank.eb.dao;

/**
 * Výjimka pro chyby interakcí s databázi
 */

public class DaoException extends RuntimeException {

    private String message;

    public DaoException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
