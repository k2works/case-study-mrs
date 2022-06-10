package mrs.application.service.room;

import mrs.IntegrationTest;
import mrs.TestDataFactory;
import mrs.domain.model.reservation.reservation.ReservableRoomList;
import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.MeetingRoom;
import mrs.domain.model.reservation.room.RoomId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;


@IntegrationTest
public class RoomServiceTest {
    @Autowired
    RoomService roomService;
    @Autowired
    TestDataFactory testDataFactory;

    @Nested
    class 会議室の検索 {
        @BeforeEach
        void setUp() {
            testDataFactory.setUp();
        }

        @Test
        void 会議室を検索する() {
            RoomId roomId = new RoomId(1);
            MeetingRoom room = roomService.findMeetingRoom(roomId);

            assertEquals(1, room.RoomId().Value());
            assertEquals("会議室A", room.RoomName());
        }

        @Test
        void 予約可能な会議室を検索する() {
            LocalDate date = LocalDate.of(2020, 1, 1);
            ReservableRoomList rooms = roomService.findReservableRooms(new ReservedDate(date)).orElse(new ReservableRoomList());

            assertEquals(3, rooms.size());
        }
    }
}
