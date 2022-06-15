package mrs.infrastructure.datasource.facility;

import mrs.domain.model.facility.room.MeetingRoom;
import mrs.domain.model.facility.room.RoomId;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.infrastructure.datasource.facility.room.RoomMapper;
import mrs.infrastructure.datasource.reservation.room.ReservableRoomMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
@DisplayName("会議室エンティティ")
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

        RoomId roomId = new RoomId(1);
        MeetingRoom actual = roomMapper.select(roomId);
        assertEquals(1, actual.RoomId().Value());
        assertEquals("会議室A", actual.RoomName());
    }

    @Test
    public void 予約可能会議室を保持している() {
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);

        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
        reservableRoomMapper.insert(reservableRoomId);
        reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 2));
        reservableRoomMapper.insert(reservableRoomId);

        MeetingRoom result = roomMapper.select(meetingRoom.RoomId());
        assertEquals(2, result.getReservableRooms().size());
    }

    @Test
    public void 会議室を検索できる() {
        roomMapper.insert(new MeetingRoom(1, "会議室A"));
        roomMapper.insert(new MeetingRoom(2, "会議室B"));

        List<MeetingRoom> result = roomMapper.selectAll();
        assertEquals(2, result.size());
    }

    @Test
    public void 会議室を更新できる() {
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);
        MeetingRoom updateMeetingRoom = new MeetingRoom(meetingRoom.RoomId().Value(), "会議室B");
        roomMapper.update(updateMeetingRoom);

        RoomId roomId = new RoomId(1);
        MeetingRoom actual = roomMapper.select(roomId);
        assertEquals(1, actual.RoomId().Value());
        assertEquals("会議室B", actual.RoomName());
    }

    @Test
    public void 会議室を削除できる() {
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室A");
        roomMapper.insert(meetingRoom);

        RoomId roomId = new RoomId(1);
        roomMapper.delete(roomId);

        MeetingRoom actual = roomMapper.select(roomId);
        assertEquals(null, actual);
    }
}
