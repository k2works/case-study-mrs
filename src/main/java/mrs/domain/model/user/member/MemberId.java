package mrs.domain.model.user.member;

import java.util.Objects;

/**
 * 会員番号
 */
public class MemberId {
    private String value;

    @Deprecated
    public MemberId() {
    }

    public MemberId(String value) {
        this.value = value;
    }

    public String Value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MemberId memberId)) return false;

        return Objects.equals(value, memberId.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MemberId{" +
                "value='" + value + '\'' +
                '}';
    }
}
