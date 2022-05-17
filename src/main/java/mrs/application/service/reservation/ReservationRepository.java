package mrs.application.service.reservation;

import mrs.domain.model.reservation.Reservation;
import mrs.domain.model.room.ReservableRoomId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(ReservableRoomId reservableRoomId);
}
