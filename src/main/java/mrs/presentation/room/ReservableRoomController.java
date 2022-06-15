package mrs.presentation.room;

import mrs.application.scenario.MeetingRoomReservationScenario;
import mrs.application.service.reservation.room.ReservableRoomService;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.infrastructure.datasource.Message;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 予約可能会議室一覧画面
 */
@Controller
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
    String roomList(Model model) {
        List<ReservableRoom> reservableRoomList = reservableRoomService.findAllReservableRooms();
        model.addAttribute("rooms", reservableRoomList);
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
