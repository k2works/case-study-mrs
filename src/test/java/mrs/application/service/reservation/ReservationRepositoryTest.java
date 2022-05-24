package mrs.application.service.reservation;

import mrs.domain.model.reserve.reservation.Reservation;
import mrs.domain.model.reserve.room.ReservableRoomId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ReservationRepositoryTest {
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    public void 予約一覧を取得する() {
        LocalDate date = LocalDate.now();
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, date);
        List<Reservation> reservations = reservationRepository.findAll();

        assertNotNull(reservations);
        assertEquals(1, reservations.size());
    }

    @Test
    public void 予約したユーザーを保持している() {
        Optional<Reservation> reservation = reservationRepository.findById(1);
        Reservation value = reservation.get();

        Assertions.assertNotNull(value);
        Assertions.assertEquals("太郎", value.getUser().getFirstName());
    }

    @Test
    public void 予約した部屋を保持している() {
        Optional<Reservation> reservation = reservationRepository.findById(1);
        Reservation value = reservation.get();

        Assertions.assertNotNull(value);
        Assertions.assertEquals(1, value.getReservableRoom().getReservableRoomId().getRoomId());
        Assertions.assertEquals(LocalDate.now(), value.getReservableRoom().getReservableRoomId().getReservedDate());
    }
}
