package mrs.domain.model.facility.room;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("施設ドメイン")
public class MeetingRoomTest {
    @Test
    void 会議室を生成できる() {
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室1");

        assertEquals(new RoomId(1), meetingRoom.RoomId());
    }

    @Test
    void 会議室番号の最大値は999() {
        assertThrows(RoomIdException.class, () -> new MeetingRoom(1000, "会議室1000"));
    }

    @Test
    void 会議室番号はフォーマット表示できる() {
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室1");

        assertEquals("001", meetingRoom.RoomNumber());
    }
}
