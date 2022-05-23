package mrs.infrastructure.datasource.reserve.room;

import mrs.domain.model.reserve.room.MeetingRoom;
import mrs.domain.model.reserve.room.ReservableRoom;
import mrs.domain.model.reserve.room.ReservableRoomId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ReservableRoomMapperTest {
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
