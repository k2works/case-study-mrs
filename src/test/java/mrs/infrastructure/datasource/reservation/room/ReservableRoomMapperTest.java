package mrs.infrastructure.datasource.reservation.room;

import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.room.MeetingRoom;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.domain.model.user.RoleName;
import mrs.domain.model.user.User;
import mrs.infrastructure.datasource.reservation.reservation.ReservationMapper;
import mrs.infrastructure.datasource.user.UserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
public class ReservableRoomMapperTest {
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private ReservableRoomMapper reservableRoomMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        reservableRoomMapper.deleteAll();
        roomMapper.deleteAll();
        reservableRoomMapper.deleteAll();
        userMapper.deleteAll();
    }

    @Test
    public void 予約可能会議室を登録できる() {
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        LocalDate reserveDate = LocalDate.of(2020, 1, 1);
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, reserveDate);
        reservableRoomMapper.insert(reservableRoomId);

        ReservableRoom reservableRoom = reservableRoomMapper.select(reservableRoomId);
        assertEquals(reservableRoomId, reservableRoom.getReservableRoomId());
        assertEquals(meetingRoom.getRoomId(), reservableRoom.getMeetingRoom().getRoomId());
        assertEquals(meetingRoom.getRoomName(), reservableRoom.getMeetingRoom().getRoomName());
    }

    @Test
    public void 予約を複数保持している() {
        User user = new User("1", "テスト", "太郎", "password", RoleName.USER);
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        Reservation reservation = new Reservation();
        reservation.setReservationId(1);
        reservation.setEndTime(LocalTime.of(10, 0));
        reservation.setStartTime(LocalTime.of(9, 0));
        reservation.setReservableRoom(reservableRoomMapper.select(reservableRoomId));
        reservation.setUser(userMapper.select(user.getUserId()));
        reservationMapper.insert(reservation);
        reservation.setReservationId(2);
        reservationMapper.insert(reservation);

        List<Reservation> reservations = reservationMapper.selectByKey(reservableRoomId.getReservedDate(), reservableRoomId.getRoomId());
        ReservableRoom result = reservableRoomMapper.select(reservableRoomId);
        assertEquals(reservations.size(), result.getReservations().size());
    }

    @Test
    public void 予約可能会議室を検索できる() {
        roomMapper.insert(new MeetingRoom(1, "会議室A"));
        roomMapper.insert(new MeetingRoom(2, "会議室B"));
        ReservableRoomId reservableRoomId1 = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        ReservableRoomId reservableRoomId2 = new ReservableRoomId(2, LocalDate.of(2020, 1, 2));
        ReservableRoomId reservableRoomId3 = new ReservableRoomId(1, LocalDate.of(2020, 2, 1));
        reservableRoomMapper.insert(reservableRoomId1);
        reservableRoomMapper.insert(reservableRoomId2);
        reservableRoomMapper.insert(reservableRoomId3);

        List<ReservableRoom> reservableRoom = reservableRoomMapper.selectAllJoin();
        assertEquals(3, reservableRoom.size());
    }

    @Test
    public void 予約可能会議室を削除できる() {
        roomMapper.insert(new MeetingRoom(1, "会議室A"));
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);
        reservableRoomMapper.delete(reservableRoomId);

        List<ReservableRoom> reservableRoom = reservableRoomMapper.selectAllJoin();
        assertEquals(0, reservableRoom.size());
    }
}