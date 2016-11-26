package ua.mykytenko.util.exceptions;

/**
 * Created by Микитенко on 20.10.2016.
 */
public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
