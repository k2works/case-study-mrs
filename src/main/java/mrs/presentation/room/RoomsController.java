package mrs.presentation.room;

import com.github.pagehelper.PageInfo;
import mrs.application.scenario.MeetingRoomReservationScenario;
import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomList;
import mrs.infrastructure.PageNation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * 会議室予約一覧画面
 */
@Controller
@RequestMapping("rooms")
public class RoomsController {

    private final MeetingRoomReservationScenario meetingRoomReservationScenario;

    public RoomsController(MeetingRoomReservationScenario meetingRoomReservationScenario) {
        this.meetingRoomReservationScenario = meetingRoomReservationScenario;
    }

    @GetMapping
    String listRooms(Model model, @RequestParam(value = "page", defaultValue = "1") int... page) {
        PageNation.startPage(page);
        LocalDate today = LocalDate.now();
        setUpModel(model, today);
        return "room/listRooms";
    }

    @GetMapping("{date}")
    String listRooms(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date, Model model, @RequestParam(value = "page", defaultValue = "1") int... page) {
        PageNation.startPage(page);
        setUpModel(model, date);
        return "room/listRooms";
    }

    private void setUpModel(Model model, LocalDate date) {
        ReservableRoomList rooms = meetingRoomReservationScenario.findReservableRooms(new ReservedDate(date)).orElse(new ReservableRoomList());
        PageInfo<ReservableRoom> pageInfo = new PageInfo<>(rooms.asList());
        model.addAttribute("date", date);
        model.addAttribute("rooms", rooms.asList());
        model.addAttribute("pageInfo", pageInfo);
    }
}
