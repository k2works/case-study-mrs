package mrs.infrastructure.datasource.reserve.reservation;

import mrs.IntegrationTest;
import mrs.domain.model.reserve.reservation.Reservation;
import mrs.domain.model.reserve.room.MeetingRoom;
import mrs.domain.model.reserve.room.ReservableRoomId;
import mrs.domain.model.user.RoleName;
import mrs.domain.model.user.User;
import mrs.infrastructure.datasource.reserve.room.ReservableRoomMapper;
import mrs.infrastructure.datasource.reserve.room.RoomMapper;
import mrs.infrastructure.datasource.user.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
public class ReservationDataSourceTest {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    ReservableRoomMapper reservableRoomMapper;
    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    ReservationDataSource reservationDataSource;

    @BeforeEach
    public void setUp() {
        userMapper.deleteAll();
        roomMapper.deleteAll();
        reservableRoomMapper.deleteAll();
        reservationMapper.deleteAll();
    }

    @Test
    public void test() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.USER);
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        mrs.domain.model.reserve.room.ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setEndTime(LocalTime.of(10, 0));
        reservation.setStartTime(LocalTime.of(9, 0));
        reservation.setReservableRoom(reservableRoomMapper.select(reservableRoomId));
        reservation.setUser(userMapper.select(user.getUserId()));
        reservationMapper.insert(reservation);

        List<Reservation> result = reservationDataSource.findByReservableRoom_ReservableRoomIdOrderByStartTimeAsc(reservableRoomId);
        assertEquals(1, result.size());
    }
}
