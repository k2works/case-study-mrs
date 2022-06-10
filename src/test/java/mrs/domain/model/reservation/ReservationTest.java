package mrs.domain.model.reservation;

import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.reservation.reservation.ReservableRoomId;
import mrs.domain.model.reservation.reservation.Reservation;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReservationTest {
    @Test
    void 予約を生成できる() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.MEMBER);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        Reservation reservation = new Reservation(
                1,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                reservableRoomId,
                user
        );

        assertEquals(1, reservation.ReservationId().Value());
        assertEquals(LocalTime.of(9, 0), reservation.ReservedTime().StartTime());
        assertEquals(LocalTime.of(10, 0), reservation.ReservedTime().EndTime());
        assertEquals(reservableRoomId, reservation.ReservableRoom().ReservableRoomId());
        assertEquals(user, reservation.User());
    }
}
