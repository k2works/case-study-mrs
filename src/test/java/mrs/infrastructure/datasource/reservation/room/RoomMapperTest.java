package mrs.infrastructure.datasource.reservation.room;

import mrs.domain.model.reservation.room.MeetingRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
public class RoomMapperTest {
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private ReservableRoomMapper reservableRoomMapper;

    @BeforeEach
    public void setUp() {
        reservableRoomMapper.deleteAll();
        roomMapper.deleteAll();
    }

    @Test
    public void 会議室を登録できる() {
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);

        MeetingRoom actual = roomMapper.select(1);
        assertEquals(1, actual.getRoomId());
        assertEquals("会議室A", actual.getRoomName());
    }

    @Test
    public void 予約可能会議室を保持している() {
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);

        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);
        reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 2));
        reservableRoomMapper.insert(reservableRoomId);

        MeetingRoom result = roomMapper.select(meetingRoom.getRoomId());
        assertEquals(2, result.getReservableRooms().size());
    }

    @Test
    public void 会議室を検索できる() {
        roomMapper.insert(new MeetingRoom(1, "会議室A"));
        roomMapper.insert(new MeetingRoom(2, "会議室B"));

        List<MeetingRoom> result = roomMapper.selectAllJoin();
        assertEquals(2, result.size());
    }

    @Test
    public void 会議室を更新できる() {
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        MeetingRoom updateMeetingRoom = new MeetingRoom(meetingRoom.getRoomId(), "会議室B");
        roomMapper.update(updateMeetingRoom);

        MeetingRoom actual = roomMapper.select(1);
        assertEquals(1, actual.getRoomId());
        assertEquals("会議室B", actual.getRoomName());
    }

    @Test
    public void 会議室を削除できる() {
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);

        roomMapper.delete(1);

        MeetingRoom actual = roomMapper.select(1);
        assertEquals(null, actual);
    }
}
