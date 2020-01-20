package ru.prostor.library.exceptions;

public class BookExistsException extends Exception{
    private String message;

    public BookExistsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
