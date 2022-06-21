package mrs.presentation.reservableRoom;

import com.github.pagehelper.PageInfo;
import mrs.application.scenario.MeetingRoomReservationScenario;
import mrs.application.service.reservation.room.ReservableRoomService;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.infrastructure.PageNation;
import mrs.infrastructure.datasource.Message;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * 予約可能会議室一覧画面
 */
@Controller
@PreAuthorize("hasRole('管理者')")
@RequestMapping("reservableRooms")
public class ReservableRoomController {
    private final ReservableRoomService reservableRoomService;

    private final MeetingRoomReservationScenario meetingRoomReservationScenario;

    private final Message message;

    public ReservableRoomController(ReservableRoomService reservableRoomService, MeetingRoomReservationScenario meetingRoomReservationScenario, Message message) {
        this.reservableRoomService = reservableRoomService;
        this.meetingRoomReservationScenario = meetingRoomReservationScenario;
        this.message = message;
    }

    @ModelAttribute
    public ReservableRoomForm setUpForm() {
        return new ReservableRoomForm();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping
    String roomList(Model model, @RequestParam(value = "page", defaultValue = "1") int... page) {
        PageNation.startPage(page);
        List<ReservableRoom> reservableRoomList = reservableRoomService.findAllReservableRooms();
        PageInfo<ReservableRoom> pageInfo = new PageInfo<>(reservableRoomList);
        Map<Integer, String> meetingRooms = meetingRoomReservationScenario.createRoomNameMap();
        model.addAttribute("rooms", reservableRoomList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("meetingRooms", meetingRooms);
        return "reservableRoom/listRooms";
    }

    @PostMapping(params = "regist")
    String creatRoom(Model model, @Validated ReservableRoomForm form, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return roomList(model);
        }
        try {
            ReservableRoomId reservableRoomId = new ReservableRoomId(form.getRoomId(), form.getReservedDate());
            meetingRoomReservationScenario.registReservableRoom(reservableRoomId);
            model.addAttribute("success", message.getMessageByKey("reservable_room_regist"));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return roomList(model);
        }
        return roomList(model);
    }

    @GetMapping("delete/{id}/{date}")
    String deleteRoom(@PathVariable("id") int id, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date, Model model) {
        try {
            ReservableRoomId reservableRoomId = new ReservableRoomId(id, date);
            reservableRoomService.deleteReservableRoom(reservableRoomId);
            model.addAttribute("success", message.getMessageByKey("reservable_room_delete"));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return roomList(model);
    }
}
