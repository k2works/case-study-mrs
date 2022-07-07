package mrs.presentation.api.reservableRoom;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mrs.application.scenario.MeetingRoomReservationScenario;
import mrs.application.service.reservation.room.ReservableRoomService;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.infrastructure.PageNation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reservableRooms")
@Tag(name = "予約可能会議室API", description = "予約可能会議室API")
@PreAuthorize("hasRole('管理者')")
public class ReservableRoomApiController {
    final ReservableRoomService reservableRoomService;

    final MeetingRoomReservationScenario meetingRoomReservationScenario;

    public ReservableRoomApiController(ReservableRoomService reservableRoomService, MeetingRoomReservationScenario meetingRoomReservationScenario) {
        this.reservableRoomService = reservableRoomService;
        this.meetingRoomReservationScenario = meetingRoomReservationScenario;
    }

    @Operation(summary = "予約可能会議室一覧の取得", description = "予約可能会議室一覧を取得する")
    @GetMapping
    PageInfo<ReservableRoom> list(@RequestParam(value = "page", defaultValue = "1") int... page) {
        PageNation.startPage(page);
        List<ReservableRoom> reservableRoomList = reservableRoomService.findAllReservableRooms();
        return new PageInfo<>(reservableRoomList);
    }

    @Operation(summary = "予約可能会議室の登録", description = "予約可能会議室を登録する")
    @PostMapping
    ReservableRoomId create(@RequestBody @Validated ReservableRoomResource resource) {
        ReservableRoomId reservableRoomId = new ReservableRoomId(resource.getRoomId(), resource.getReservedDate());
        meetingRoomReservationScenario.registReservableRoom(reservableRoomId);
        return reservableRoomId;
    }

    @Operation(summary = "会議室の削除", description = "会議室を削除する")
    @DeleteMapping("/{roomId}/{reservedDate}")
    void delete(
            @PathVariable("roomId") int id,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("reservedDate") LocalDate date) {
        ReservableRoomId reservableRoomId = new ReservableRoomId(id, date);
        reservableRoomService.deleteReservableRoom(reservableRoomId);
    }
}
