package mrs.application.service.reservation;

import mrs.domain.model.reservation.reservation.ReservableRoomId;
import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.reservation.ReservationId;

import java.util.List;
import java.util.Optional;

/**
 * 予約リポジトリ
 */
public interface ReservationRepository {
    List<Reservation> findByReservedDateAndRoomIdOrderByStartTimeAsc(ReservableRoomId reservableRoomId);

    void save(Reservation reservation);

    void delete(Reservation reservation);

    Reservation getById(ReservationId reservationId);

    List<Reservation> findAll();

    Optional<Reservation> findById(ReservationId reservationId);
}
