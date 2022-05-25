package mrs.application.service.reservation;

import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.room.ReservableRoomId;

import java.util.List;
import java.util.Optional;

/**
 * 予約リポジトリ
 */
public interface ReservationRepository {
    List<Reservation> findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(ReservableRoomId reservableRoomId);

    void save(Reservation reservation);

    void delete(Reservation reservation);

    Reservation getById(Integer reservationId);

    List<Reservation> findAll();

    Optional<Reservation> findById(int i);
}
