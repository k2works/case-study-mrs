package mrs.domain.model.facility.room;

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
        if (value > 999) throw new RoomIdException("会議室番号は999以下である必要があります");

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

    public String format() {
        return String.format("%03d", value);
    }
}
