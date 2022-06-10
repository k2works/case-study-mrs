package mrs;

import mrs.domain.model.reservation.reservation.ReservableRoomId;
import mrs.infrastructure.datasource.auth.UserMapper;
import mrs.infrastructure.datasource.reservation.reservation.ReservationMapper;
import mrs.infrastructure.datasource.reservation.room.ReservableRoomMapper;
import mrs.infrastructure.datasource.reservation.room.RoomMapper;

public interface TestDataFactory {
    void setUp();

    UserMapper getUserMapper();

    RoomMapper getRoomMapper();

    ReservableRoomMapper getReservableRoomMapper();

    ReservationMapper getReservationMapper();

    ReservableRoomId getReservableRoomId();

}
