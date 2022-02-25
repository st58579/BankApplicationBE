package cz.upce.bank.eb.controller;

/**
 * Vyjímka pro pokus o neoprávněný přístup
 */

public class AccessProhibitedException extends RuntimeException {

    public AccessProhibitedException(String message) {
        super(message);
    }
}
