package mrs.presentation.room;

import mrs.application.service.room.RoomService;
import mrs.domain.model.reservation.reservation.ReservableRoom;
import mrs.domain.model.reservation.reservation.ReservableRoomId;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.List;

/**
 * 予約可能会議室一覧画面
 */
@Controller
@RequestMapping("reservableRooms")
public class ReservableRoomController {
    private final RoomService roomService;

    public ReservableRoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    String roomList(Model model) {
        List<ReservableRoom> reservableRoomList = roomService.findAllReservableRooms();
        model.addAttribute("rooms", reservableRoomList);
        return "reservableRoom/listRooms";
    }

    @PostMapping(params = "regist")
    String creatRoom(Model model) {
        ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2022, 1, 1));
        roomService.registReservableRoom(reservableRoomId);
        return roomList(model);
    }

    @GetMapping("delete/{id}/{date}")
    String deleteRoom(@PathVariable("id") int id, @PathVariable("date") LocalDate date, Model model) {
        ReservableRoomId reservableRoomId = new ReservableRoomId(id, date);
        roomService.deleteReservableRoom(reservableRoomId);
        return roomList(model);
    }
}
