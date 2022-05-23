package mrs.infrastructure.datasource.reserve.room;

import mrs.domain.model.reserve.room.MeetingRoom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
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

        meetingRoom.setRoomName("会議室B");
        roomMapper.update(meetingRoom);

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
