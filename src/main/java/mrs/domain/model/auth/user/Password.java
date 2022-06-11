package mrs.domain.model.auth.user;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * パスワード
 */
public class Password {
    private String value;

    @Deprecated
    public Password() {
    }

    public Password(String value) {
        if (value == null || value.isEmpty()) {
            this.value = "";
        } else {
            checkPolicy(value);
            this.value = value;
        }
    }

    private void checkPolicy(String value) {
        if (value.length() < 8) {
            throw new PasswordException("パスワードは8文字以上である必要があります");
        }

        String regex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        if (!matcher.find()) throw new PasswordException("パスワードは小文字、大文字、数字を含む必要があります");
    }

    public String Value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Password that)) return false;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Password{" +
                "value=" + value +
                '}';
    }
}
