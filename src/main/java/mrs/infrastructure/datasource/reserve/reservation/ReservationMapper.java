package mrs.infrastructure.datasource.reserve.reservation;

import mrs.domain.model.reserve.reservation.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReservationMapper {
    void insert(Reservation reservation);

    Reservation select(Integer reservationId);

    List<Reservation> selectAllJoin();

    void update(Reservation reservation);

    void delete(Integer reservationId);

    void deleteAll();
}
