package mrs.domain.model.auth.user;

/**
 * 利用者例外
 */
public class UserException extends RuntimeException {
    public UserException(String message) {
        super(message);
    }
}
