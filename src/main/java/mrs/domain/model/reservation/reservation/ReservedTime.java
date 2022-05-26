package mrs.domain.model.reservation.reservation;

import java.time.LocalTime;
import java.util.Objects;

/**
 * 予約時間
 */
public class ReservedTime {
    LocalTime startTime;
    LocalTime endTime;

    @Deprecated
    public ReservedTime() {
    }

    public ReservedTime(LocalTime startTime, LocalTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public LocalTime StartTime() {
        return startTime;
    }

    public LocalTime EndTime() {
        return endTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservedTime that)) return false;

        if (!Objects.equals(startTime, that.startTime)) return false;
        return Objects.equals(endTime, that.endTime);
    }

    @Override
    public int hashCode() {
        int result = startTime != null ? startTime.hashCode() : 0;
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReservedTime{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public boolean overlap(ReservedTime target) {
        if (startTime.equals(target.startTime) && endTime.equals(target.endTime)) {
            return true;
        }
        return target.endTime.isAfter(startTime) && endTime.isAfter(target.startTime);
    }
}
