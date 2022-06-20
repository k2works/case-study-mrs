package mrs.presentation.room;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mrs.application.scenario.MeetingRoomReservationScenario;
import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomList;
import mrs.infrastructure.datasource.PageNation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.HashMap;

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
    String listRooms(Model model, @RequestParam HashMap<String, String> params) {
        int startPage = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        LocalDate today = LocalDate.now();
        setUpModel(model, startPage, today);
        return "room/listRooms";
    }

    @GetMapping("{date}")
    String listRooms(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date, Model model, @RequestParam HashMap<String, String> params) {
        int startPage = params.get("page") == null ? 1 : Integer.parseInt(params.get("page"));
        setUpModel(model, startPage, date);
        return "room/listRooms";
    }

    private void setUpModel(Model model, int startPage, LocalDate date) {
        PageHelper.startPage(startPage, PageNation.PAGE_SIZE);
        ReservableRoomList rooms = meetingRoomReservationScenario.findReservableRooms(new ReservedDate(date)).orElse(new ReservableRoomList());
        PageInfo<ReservableRoom> pageInfo = new PageInfo<>(rooms.asList());
        int page = pageInfo.getPageNum();
        int endPage = pageInfo.getPages();
        long totalPage = pageInfo.getTotal();
        PageNation pageNation = new PageNation(page, startPage, endPage, totalPage);
        model.addAttribute("date", date);
        model.addAttribute("rooms", rooms.asList());
        model.addAttribute("pageNation", pageNation);
    }
}
