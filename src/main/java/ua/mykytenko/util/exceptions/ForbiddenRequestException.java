package ua.mykytenko.util.exceptions;

/**
 * Created by Микитенко on 06.11.2016.
 */
public class ForbiddenRequestException extends RuntimeException{
    public ForbiddenRequestException(String message) {
        super(message);
    }
}
