package mrs.infrastructure.datasource.reservation.room;

import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.facility.room.MeetingRoom;
import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.infrastructure.datasource.auth.UserMapper;
import mrs.infrastructure.datasource.facility.room.RoomMapper;
import mrs.infrastructure.datasource.reservation.reservation.ReservationMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@DisplayName("予約可能会議室エンティティ")
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

    private User newUser() {
        return new User("U999999", "テスト", "太郎", "a234567Z", RoleName.一般);
    }

    @Test
    public void 予約可能会議室を登録できる() {
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        LocalDate reserveDate = LocalDate.of(2020, 1, 1);
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, reserveDate);
        reservableRoomMapper.insert(reservableRoomId);

        ReservableRoom reservableRoom = reservableRoomMapper.select(reservableRoomId);
        assertEquals(reservableRoomId, reservableRoom.ReservableRoomId());
        assertEquals(meetingRoom.RoomId(), reservableRoom.MeetingRoom().RoomId());
        assertEquals(meetingRoom.RoomName(), reservableRoom.MeetingRoom().RoomName());
    }

    @Test
    public void 予約を複数保持している() {
        User user = newUser();
        userMapper.insert(user);
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);

        Reservation reservation = new Reservation(
                1,
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                reservableRoomId,
                userMapper.select(user.UserId())
        );
        reservationMapper.insert(reservation);
        Reservation addReservation = new Reservation(
                2,
                reservation.ReservedTime().StartTime(),
                reservation.ReservedTime().EndTime(),
                reservableRoomId,
                userMapper.select(user.UserId())
        );
        reservationMapper.insert(addReservation);

        List<Reservation> reservations = reservationMapper.selectByKey(reservableRoomId.ReservedDate().Value(), reservableRoomId.RoomId().Value());
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

        List<ReservableRoom> reservableRoom = reservableRoomMapper.selectAll();
        assertEquals(3, reservableRoom.size());
    }

    @Test
    public void 予約可能会議室を削除できる() {
        roomMapper.insert(new MeetingRoom(1, "会議室A"));
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);
        reservableRoomMapper.delete(reservableRoomId);

        List<ReservableRoom> reservableRoom = reservableRoomMapper.selectAll();
        assertEquals(0, reservableRoom.size());
    }
}
