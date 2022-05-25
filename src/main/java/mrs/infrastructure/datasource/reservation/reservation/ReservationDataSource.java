package mrs.infrastructure.datasource.reservation.reservation;

import mrs.application.service.reservation.ReservationRepository;
import mrs.domain.model.reservation.reservation.Reservation;
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
    public List<Reservation> findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(ReservableRoomId reservableRoomId) {
        return reservationMapper.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId);
    }

    @Override
    public void save(Reservation reservation) {
        reservationMapper.insert(reservation);
    }

    @Override
    public void delete(Reservation reservation) {
        reservationMapper.delete(reservation.getReservationId());
    }

    @Override
    public Reservation getById(Integer reservationId) {
        return reservationMapper.select(reservationId);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationMapper.selectAllJoin();
    }

    @Override
    public Optional<Reservation> findById(int i) {
        return Optional.ofNullable(reservationMapper.select(i));
    }
}
