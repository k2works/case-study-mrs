package mrs.domain.model.reservation.reservation;

import java.time.LocalDate;
import java.util.Objects;

/**
 * 予約日
 */
public class ReservedDate {
    private LocalDate value;

    @Deprecated
    public ReservedDate() {
    }

    public ReservedDate(LocalDate value) {
        this.value = value;
    }

    public LocalDate Value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservedDate that)) return false;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ReservedDate{" +
                "value=" + value +
                '}';
    }
}
