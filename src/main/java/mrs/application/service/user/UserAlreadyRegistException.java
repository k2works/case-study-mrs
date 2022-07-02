package mrs.application.service.user;

public class UserAlreadyRegistException extends RuntimeException {
    public UserAlreadyRegistException(String message) {
        super(message);
    }
}
