package mrs.domain.model.reservation.room;

import java.util.Objects;

/**
 * 会議室番号
 */
public class RoomId {
    private Integer value;

    @Deprecated
    public RoomId() {
    }

    public RoomId(Integer value) {
        this.value = value;
    }

    public Integer Value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomId roomId)) return false;

        return Objects.equals(value, roomId.value);
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RoomId{" +
                "value=" + value +
                '}';
    }
}
