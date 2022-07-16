package mrs.presentation.api.meetingRoom;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mrs.application.scenario.MeetingRoomReservationScenario;
import mrs.application.service.facility.room.MeetingRoomService;
import mrs.domain.model.facility.room.MeetingRoom;
import mrs.infrastructure.PageNation;
import mrs.infrastructure.datasource.Message;
import mrs.infrastructure.security.jwt.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 会議室API
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/meetingRooms")
@Tag(name = "会議室API", description = "会議室API")
@PreAuthorize("hasRole('管理者')")
public class MeetingRoomApiController {
    final MeetingRoomService meetingRoomService;
    final MeetingRoomReservationScenario meetingRoomReservationScenario;
    final Message message;

    public MeetingRoomApiController(MeetingRoomReservationScenario meetingRoomReservationScenario, MeetingRoomService meetingRoomService, Message message) {
        this.meetingRoomReservationScenario = meetingRoomReservationScenario;
        this.meetingRoomService = meetingRoomService;
        this.message = message;
    }

    @Operation(summary = "会議室一覧の取得", description = "会議室一覧を取得する")
    @GetMapping
    ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "1") int... page) {
        try {
            PageNation.startPage(page);
            List<MeetingRoom> meetingRoomList = meetingRoomService.findAll();
            PageInfo<MeetingRoom> pageInfo = new PageInfo<>(meetingRoomList);
            return ResponseEntity.ok(pageInfo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @Operation(summary = "会議室の登録", description = "会議室を登録する")
    @PostMapping
    ResponseEntity<?> create(@RequestBody @Validated MeetingRoomResource resource) {
        try {
            MeetingRoom meetingRoom = new MeetingRoom(resource.getRoomId(), resource.getRoomName());
            meetingRoomReservationScenario.registMeetingRoom(meetingRoom);
            return ResponseEntity.ok(new MessageResponse(message.getMessageByKey("meeting_room_regist")));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @Operation(summary = "会議室の更新", description = "会議室を更新する")
    @PutMapping("/{roomId}")
    ResponseEntity<?> update(@PathVariable Integer roomId, @RequestBody @Validated MeetingRoomResource resource) {
        try {
            MeetingRoom meetingRoom = new MeetingRoom(roomId, resource.getRoomName());
            meetingRoomService.updateMeetingRoom(meetingRoom);
            return ResponseEntity.ok(new MessageResponse(message.getMessageByKey("meeting_room_update")));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @Operation(summary = "会議室の削除", description = "会議室を削除する")
    @DeleteMapping("/{roomId}")
    ResponseEntity<?> delete(@PathVariable Integer roomId) {
        try {
            meetingRoomService.deleteMeetingRoom(roomId);
            return ResponseEntity.ok(new MessageResponse(message.getMessageByKey("meeting_room_delete")));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @Operation(summary = "会議室リストボックス", description = "会議室リストボックスを取得する")
    @GetMapping("/listbox")
    Map<Integer, String> listbox() {
        return meetingRoomService.createRoomNameMap();
    }
}
