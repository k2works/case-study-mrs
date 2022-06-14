package mrs.presentation.room;

import mrs.application.service.room.RoomService;
import mrs.domain.model.reservation.room.MeetingRoom;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 会議室一覧画面
 */
@Controller
@RequestMapping("meetingRooms")
public class MeetingRoomController {
    private final RoomService roomService;

    public MeetingRoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    String roomList(Model model) {
        List<MeetingRoom> meetingRoomList = roomService.findAll();
        model.addAttribute("rooms", meetingRoomList);
        return "meetingRoom/listRooms";
    }

    @PostMapping(params = "regist")
    String creatRoom(Model model) {
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室1");
        roomService.registMeetingRoom(meetingRoom);
        return roomList(model);
    }

    @PostMapping(params = "update")
    String updateRoom(Model model) {
        MeetingRoom meetingRoom = new MeetingRoom(1, "会議室1");
        roomService.updateMeetingRoom(meetingRoom);
        return roomList(model);
    }

    @GetMapping("delete/{id}")
    String deleteRoom(@PathVariable("id") int id, Model model) {
        roomService.deleteMeetingRoom(id);
        return roomList(model);
    }
}
