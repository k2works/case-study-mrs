package mrs.domain.model.reservation.room;

import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.reservation.reservation.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("予約ドメイン")
public class ReservationTest {
    private User newUser() {
        return new User("U999999", "テスト", "太郎", "a234567Z", RoleName.一般);
    }

    @Test
    void 予約を生成できる() {
        User user = newUser();
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
