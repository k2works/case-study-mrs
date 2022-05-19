package mrs.appication.service.room;

import mrs.Application;
import mrs.application.service.room.RoomService;
import mrs.domain.model.room.ReservableRoom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = Application.class)
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
