package mrs.infrastructure.datasource.reservation.reservation;

import mrs.application.service.reservation.reservation.ReservationRepository;
import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.reservation.ReservationId;
import mrs.domain.model.reservation.room.ReservableRoomId;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReservationDataSource implements ReservationRepository {

    ReservationMapper reservationMapper;

    public ReservationDataSource(ReservationMapper reservationMapper) {
        this.reservationMapper = reservationMapper;
    }

    @Override
    public List<Reservation> findByReservedDateAndRoomIdOrderByStartTimeAsc(ReservableRoomId reservableRoomId) {
        return reservationMapper.selectByReservedDateAndRoomIdOrderByStartTimeAsc(reservableRoomId);
    }

    @Override
    public void save(Reservation reservation) {
        reservationMapper.insert(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationMapper.delete(reservation.ReservationId());
    }

    @Override
    public Reservation getById(ReservationId reservationId) {
        return reservationMapper.select(reservationId);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationMapper.selectAll();
    }

    @Override
    public Optional<Reservation> findById(ReservationId reservationId) {
        return Optional.ofNullable(reservationMapper.select(reservationId));
    }
}
