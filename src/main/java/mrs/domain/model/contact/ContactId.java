package mrs.domain.model.contact;

import java.util.Objects;

/**
 * 問い合わせ番号
 */
public class ContactId {
    String value;

    @Deprecated
    public ContactId() {
    }

    public ContactId(String value) {
        this.value = value;
    }

    public String Value() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ContactId other = (ContactId) obj;
        return Objects.equals(this.value, other.value);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "ContactId{" +
                "value=" + value +
                '}';
    }
}
