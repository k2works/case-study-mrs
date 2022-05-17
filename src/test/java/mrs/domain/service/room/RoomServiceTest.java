package mrs.domain.service.room;

import mrs.MrsApplication;
import mrs.application.service.room.RoomService;
import mrs.domain.model.ReservableRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = MrsApplication.class)
@ActiveProfiles("dev")
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
