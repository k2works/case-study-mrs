package mrs.domain.model.reservation.reservation;

import java.util.Objects;

/**
 * 予約番号
 */
public class ReservationId {
    private Integer value;

    @Deprecated
    public ReservationId() {
    }

    public ReservationId(Integer value) {
        this.value = value;
    }

    public Integer Value() {
        return value;
    }

    @Deprecated

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationId that)) return false;

        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ReservationId{" +
                "value=" + value +
                '}';
    }
}
