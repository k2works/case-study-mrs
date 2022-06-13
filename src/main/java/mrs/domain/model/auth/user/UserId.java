package mrs.domain.model.auth.user;

import java.util.Objects;

/**
 * 利用者番号
 */
public class UserId {
    private String value;

    @Deprecated
    public UserId() {
    }

    public UserId(String value) {
        if (value.charAt(0) != 'U') throw new UserIdException("利用者番号の先頭はUから始まります");
        if (value.length() != 7) throw new UserIdException("利用者番号の長さは7文字です");
        StringBuilder sb = new StringBuilder(value);
        String result = sb.substring(1, 7);
        if (!result.matches("\\d+")) throw new UserIdException("利用者番号は数字です");

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
