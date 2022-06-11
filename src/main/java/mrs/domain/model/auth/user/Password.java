package mrs.domain.model.auth.user;

import java.util.Objects;

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
            this.value = value;
        }
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
