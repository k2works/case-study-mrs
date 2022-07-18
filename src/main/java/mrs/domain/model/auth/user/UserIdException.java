package mrs.domain.model.auth.user;

/**
 * 利用者番号例外
 */
public class UserIdException extends RuntimeException {
    public UserIdException(String message) {
        super(message);
    }
}
