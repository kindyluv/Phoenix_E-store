package africa.semicolon.phoenix.web.Exceptions;

public class ProductDoesNotExistException extends Throwable {
    public ProductDoesNotExistException(String message) {
        super(message);
    }
}
