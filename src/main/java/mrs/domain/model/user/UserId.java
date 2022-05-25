package mrs.domain.model.user;

import java.util.Objects;

/**
 * 会員番号
 */
public class UserId {
    private String value;

    @Deprecated
    public UserId() {
    }

    public UserId(String value) {
        this.value = value;
    }

    public String Value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserId userId)) return false;

        return Objects.equals(value, userId.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "UserId{" +
                "value='" + value + '\'' +
                '}';
    }
}
