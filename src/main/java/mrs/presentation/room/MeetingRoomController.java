package mrs.presentation.room;

import mrs.application.service.room.MeetingRoomService;
import mrs.application.service.room.ReservableRoomService;
import mrs.domain.model.property.room.MeetingRoom;
import mrs.infrastructure.datasource.Message;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会議室一覧画面
 */
@Controller
@RequestMapping("meetingRooms")
public class MeetingRoomController {
    private final ReservableRoomService reservableRoomService;

    private final MeetingRoomService meetingRoomService;

    private final Message message;

    public MeetingRoomController(ReservableRoomService reservableRoomService, MeetingRoomService meetingRoomService, Message message) {
        this.reservableRoomService = reservableRoomService;
        this.meetingRoomService = meetingRoomService;
        this.message = message;
    }

    @ModelAttribute
    public MeetingRoomForm setUpForm() {
        return new MeetingRoomForm();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping
    String roomList(Model model) {
        List<MeetingRoom> meetingRoomList = meetingRoomService.findAll();
        model.addAttribute("rooms", meetingRoomList);
        return "meetingRoom/listRooms";
    }

    @PostMapping(params = "regist")
    String creatRoom(Model model, @Validated MeetingRoomForm form, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return roomList(model);
        }
        try {
            MeetingRoom meetingRoom = new MeetingRoom(form.getRoomId(), form.getRoomName());
            meetingRoomService.registMeetingRoom(meetingRoom);
            model.addAttribute("success", message.getMessageByKey("meeting_room_regist"));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return roomList(model);
        }
        return roomList(model);
    }

    @PostMapping(params = "update")
    String updateRoom(Model model, @Validated MeetingRoomForm form, BindingResult result) {
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            return roomList(model);
        }
        try {
            MeetingRoom meetingRoom = new MeetingRoom(form.getRoomId(), form.getRoomName());
            meetingRoomService.updateMeetingRoom(meetingRoom);
            model.addAttribute("success", message.getMessageByKey("meeting_room_update"));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return roomList(model);
        }
        return roomList(model);
    }

    @GetMapping("delete/{id}")
    String deleteRoom(@PathVariable("id") int id, Model model) {
        try {
            meetingRoomService.deleteMeetingRoom(id);
            model.addAttribute("success", message.getMessageByKey("meeting_room_delete"));
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return roomList(model);
        }
        return roomList(model);
    }
}
