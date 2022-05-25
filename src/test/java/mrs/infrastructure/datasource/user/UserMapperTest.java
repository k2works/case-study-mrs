package mrs.infrastructure.datasource.user;

import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.room.MeetingRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.domain.model.user.RoleName;
import mrs.domain.model.user.User;
import mrs.infrastructure.datasource.reservation.reservation.ReservationMapper;
import mrs.infrastructure.datasource.reservation.room.ReservableRoomMapper;
import mrs.infrastructure.datasource.reservation.room.RoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
public class UserMapperTest {
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private ReservableRoomMapper reservableRoomMapper;
    @Autowired
    private ReservationMapper reservationMapper;
    @Autowired
    UserMapper userMapper;

    @BeforeEach
    public void setUp() {
        reservableRoomMapper.deleteAll();
        roomMapper.deleteAll();
        reservableRoomMapper.deleteAll();
        userMapper.deleteAll();
    }

    @Test
    public void ユーザーが登録できる() {
        userMapper.insert(new User("1", "テスト", "太郎", "password", RoleName.USER));

        User user = userMapper.select("1");
        assert (user.getUserId().equals("1"));
        assert (user.getFirstName().equals("テスト"));
        assert (user.getLastName().equals("太郎"));
        assert (user.getPassword().equals("password"));
        assert (user.getRoleName().equals(RoleName.USER));
    }

    @Test
    public void ユーザーを検索できる() {
        userMapper.insert(new User("1", "テスト", "太郎", "password", RoleName.USER));
        userMapper.insert(new User("2", "テスト", "太郎", "password", RoleName.USER));

        List<User> users = userMapper.selectAllJoin();
        assert (users.size() == 2);
    }

    @Test
    public void 複数の予約を保持している() {
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
        User result = userMapper.select(user.getUserId());
        assertEquals(reservations.size(), result.getReservations().size());
    }

    @Test
    public void ユーザーを更新できる() {
        userMapper.insert(new User("3", "テスト", "太郎", "password", RoleName.USER));

        User user = userMapper.select("3");
        user.setFirstName("更新1");
        user.setLastName("更新2");
        user.setPassword("updated");
        user.setRoleName(RoleName.ADMIN);
        userMapper.update(user);

        User updatedUser = userMapper.select("3");
        assert (updatedUser.getFirstName().equals("更新1"));
        assert (updatedUser.getLastName().equals("更新2"));
        assert (updatedUser.getPassword().equals("updated"));
        assert (updatedUser.getRoleName().equals(RoleName.ADMIN));
    }

    @Test
    public void ユーザーを削除できる() {
        userMapper.insert(new User("4", "テスト", "太郎", "password", RoleName.USER));

        userMapper.delete("4");
        User user = userMapper.select("4");
        assert (user == null);
    }
}
