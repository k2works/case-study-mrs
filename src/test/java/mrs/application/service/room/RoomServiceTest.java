package mrs.application.service.room;

import mrs.IntegrationTest;
import mrs.domain.model.reserve.room.ReservableRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@IntegrationTest
public class RoomServiceTest {
    @Autowired
    RoomService roomService;

    @Test
    public void 会議室一覧を取得する() {
        LocalDate date = LocalDate.now();
        List<ReservableRoom> rooms = roomService.findReservableRooms(date);

        assertEquals(2, rooms.size());
    }
}
