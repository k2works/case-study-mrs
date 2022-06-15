package mrs;

import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.infrastructure.datasource.auth.UserMapper;
import mrs.infrastructure.datasource.facility.room.RoomMapper;
import mrs.infrastructure.datasource.reservation.reservation.ReservationMapper;
import mrs.infrastructure.datasource.reservation.room.ReservableRoomMapper;

public interface TestDataFactory {
    void setUp();

    UserMapper getUserMapper();

    RoomMapper getRoomMapper();

    ReservableRoomMapper getReservableRoomMapper();

    ReservationMapper getReservationMapper();

    ReservableRoomId getReservableRoomId();

}
