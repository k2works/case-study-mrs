package mrs.infrastructure.datasource.reserve.reservation;

import mrs.domain.model.reserve.reservation.Reservation;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ReservationMapper {
    void insert(Reservation reservation);

    Reservation select(Integer reservationId);

    List<Reservation> selectAllJoin();

    List<Reservation> selectByKey(@Param("reservedDate") LocalDate reservedDate, @Param("roomId") Integer roomId);

    List<Reservation> selectByUserId(String userId);

    void update(Reservation reservation);

    void delete(Integer reservationId);

    void deleteAll();
}
