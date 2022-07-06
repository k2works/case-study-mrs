package mrs.presentation.api.meetingRoom;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mrs.application.scenario.MeetingRoomReservationScenario;
import mrs.application.service.facility.room.MeetingRoomService;
import mrs.domain.model.facility.room.MeetingRoom;
import mrs.infrastructure.PageNation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/meetingRooms")
@Tag(name = "会議室API", description = "会議室API")
@PreAuthorize("hasRole('管理者')")
public class MeetingRoomApiController {
    final MeetingRoomService meetingRoomService;
    final MeetingRoomReservationScenario meetingRoomReservationScenario;

    public MeetingRoomApiController(MeetingRoomReservationScenario meetingRoomReservationScenario, MeetingRoomService meetingRoomService) {
        this.meetingRoomReservationScenario = meetingRoomReservationScenario;
        this.meetingRoomService = meetingRoomService;
    }

    @Operation(summary = "会議室一覧の取得", description = "会議室一覧を取得する")
    @GetMapping
    PageInfo<MeetingRoom> list(@RequestParam(value = "page", defaultValue = "1") int... page) {
        PageNation.startPage(page);
        List<MeetingRoom> meetingRoomList = meetingRoomService.findAll();
        return new PageInfo<>(meetingRoomList);
    }

    @Operation(summary = "会議室の登録", description = "会議室を登録する")
    @PostMapping
    MeetingRoom create(@RequestBody @Validated MeetingRoomResource resource) {
        MeetingRoom meetingRoom = new MeetingRoom(resource.getRoomId(), resource.getRoomName());
        meetingRoomReservationScenario.registMeetingRoom(meetingRoom);
        return meetingRoom;
    }

    @Operation(summary = "会議室の更新", description = "会議室を更新する")
    @PutMapping("/{roomId}")
    MeetingRoom update(@PathVariable Integer roomId, @RequestBody @Validated MeetingRoomResource resource) {
        MeetingRoom meetingRoom = new MeetingRoom(roomId, resource.getRoomName());
        meetingRoomService.updateMeetingRoom(meetingRoom);
        return meetingRoom;
    }

    @Operation(summary = "会議室の削除", description = "会議室を削除する")
    @DeleteMapping("/{roomId}")
    void delete(@PathVariable Integer roomId) {
        meetingRoomService.deleteMeetingRoom(roomId);
    }
}
