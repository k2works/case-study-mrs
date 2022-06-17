package mrs;

import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.facility.room.MeetingRoom;
import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.infrastructure.datasource.auth.UserMapper;
import mrs.infrastructure.datasource.facility.room.RoomMapper;
import mrs.infrastructure.datasource.reservation.reservation.ReservationMapper;
import mrs.infrastructure.datasource.reservation.room.ReservableRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestDataFactoryImpl implements TestDataFactory {
    @Autowired
    UserMapper userMapper;
    @Autowired
    RoomMapper roomMapper;
    @Autowired
    ReservableRoomMapper reservableRoomMapper;
    @Autowired
    ReservationMapper reservationMapper;

    public void setUp() {
        delete();
        create();
    }

    public void create() {
        createUser();
        createMeetingRoom();
        createReservableRoom();
        createReservation();
    }

    public void delete() {
        reservationMapper.deleteAll();
        reservableRoomMapper.deleteAll();
        roomMapper.deleteAll();
        userMapper.deleteAll();
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public RoomMapper getRoomMapper() {
        return roomMapper;
    }

    public ReservableRoomMapper getReservableRoomMapper() {
        return reservableRoomMapper;
    }

    public ReservationMapper getReservationMapper() {
        return reservationMapper;
    }

    public ReservableRoomId getReservableRoomId() {
        return new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
    }

    private User newUser() {
        return new User("U999999", "テスト", "太郎", "a234567Z", RoleName.一般);
    }

    void createUser() {
        User user = newUser();
        userMapper.insert(user);
    }

    void createMeetingRoom() {
        List<MeetingRoom> meetingRooms = new ArrayList<>();
        meetingRooms.add(new MeetingRoom(1, "会議室A"));
        meetingRooms.add(new MeetingRoom(2, "会議室B"));
        meetingRooms.add(new MeetingRoom(3, "会議室C"));
        meetingRooms.forEach(roomMapper::insert);
    }

    void createReservableRoom() {
        List<ReservableRoomId> reservableRoomIds = new ArrayList<>();
        ReservableRoomId reservableRoomId = this.getReservableRoomId();
        reservableRoomIds.add(reservableRoomId);
        reservableRoomIds.add(new ReservableRoomId(2, LocalDate.of(2020, 1, 1)));
        reservableRoomIds.add(new ReservableRoomId(3, LocalDate.of(2020, 1, 1)));
        reservableRoomIds.forEach(reservableRoomMapper::insert);
    }

    void createReservation() {
        User user = newUser();
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        Reservation reservation = new Reservation(1, LocalTime.of(9, 0), LocalTime.of(10, 0), reservableRoomId, user);
        reservationMapper.insert(reservation);
    }

}
