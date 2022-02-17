package africa.semicolon.phoenix.web.Exceptions;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String message) {
        super(message);
    }
}
