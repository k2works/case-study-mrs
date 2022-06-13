package mrs.application.service.auth;

public class UserAlreadyRegistException extends RuntimeException {
    public UserAlreadyRegistException(String message) {
        super(message);
    }
}
