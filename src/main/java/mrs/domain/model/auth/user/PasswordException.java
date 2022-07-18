package mrs.domain.model.auth.user;

/**
 * パスワード例外
 */
public class PasswordException extends RuntimeException {
    public PasswordException(String message) {
        super(message);
    }
}
