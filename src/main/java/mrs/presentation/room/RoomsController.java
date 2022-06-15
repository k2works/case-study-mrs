package mrs.presentation.room;

import mrs.application.service.room.ReservableRoomService;
import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.ReservableRoomList;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

/**
 * 会議室一覧画面
 */
@Controller("会議室一覧")
@RequestMapping("rooms")
public class RoomsController {
    private final ReservableRoomService reservableRoomService;

    public RoomsController(ReservableRoomService reservableRoomService) {
        this.reservableRoomService = reservableRoomService;
    }

    @GetMapping
    String listRooms(Model model) {
        LocalDate today = LocalDate.now();
        ReservableRoomList rooms = reservableRoomService.findReservableRooms(new ReservedDate(today)).orElse(new ReservableRoomList());
        model.addAttribute("date", today);
        model.addAttribute("rooms", rooms.asList());
        return "room/listRooms";
    }

    @GetMapping("{date}")
    String listRooms(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date, Model model) {
        ReservableRoomList rooms = reservableRoomService.findReservableRooms(new ReservedDate(date)).orElse(new ReservableRoomList());
        model.addAttribute("rooms", rooms.asList());
        return "room/listRooms";
    }
}
