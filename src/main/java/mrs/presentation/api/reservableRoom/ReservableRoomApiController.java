package mrs.presentation.api.reservableRoom;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mrs.application.scenario.MeetingRoomReservationScenario;
import mrs.application.service.reservation.room.ReservableRoomService;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.infrastructure.PageNation;
import mrs.infrastructure.datasource.Message;
import mrs.infrastructure.security.jwt.payload.response.MessageResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * 予約可能会議室API
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reservableRooms")
@Tag(name = "予約可能会議室API", description = "予約可能会議室API")
@PreAuthorize("hasRole('管理者')")
public class ReservableRoomApiController {
    final ReservableRoomService reservableRoomService;

    final MeetingRoomReservationScenario meetingRoomReservationScenario;

    final Message message;

    public ReservableRoomApiController(ReservableRoomService reservableRoomService, MeetingRoomReservationScenario meetingRoomReservationScenario, Message message) {
        this.reservableRoomService = reservableRoomService;
        this.meetingRoomReservationScenario = meetingRoomReservationScenario;
        this.message = message;
    }

    @Operation(summary = "予約可能会議室一覧の取得", description = "予約可能会議室一覧を取得する")
    @GetMapping
    ResponseEntity<?> list(@RequestParam(value = "page", defaultValue = "1") int... page) {
        try {
            PageNation.startPage(page);
            List<ReservableRoom> reservableRoomList = reservableRoomService.findAllReservableRooms();
            PageInfo<ReservableRoom> pageInfo = new PageInfo<>(reservableRoomList);
            return ResponseEntity.ok(pageInfo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @Operation(summary = "予約可能会議室の登録", description = "予約可能会議室を登録する")
    @PostMapping
    ResponseEntity<?> create(@RequestBody @Validated ReservableRoomResource resource) {
        try {
            ReservableRoomId reservableRoomId = new ReservableRoomId(resource.getRoomId(), resource.getReservedDate());
            meetingRoomReservationScenario.registReservableRoom(reservableRoomId);
            return ResponseEntity.ok(new MessageResponse(message.getMessageByKey("reservable_room_regist")));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @Operation(summary = "会議室の削除", description = "会議室を削除する")
    @DeleteMapping("/{roomId}/{reservedDate}")
    ResponseEntity<?> delete(
            @PathVariable("roomId") int id,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("reservedDate") LocalDate date) {
        try {
            ReservableRoomId reservableRoomId = new ReservableRoomId(id, date);
            reservableRoomService.deleteReservableRoom(reservableRoomId);
            return ResponseEntity.ok(new MessageResponse(message.getMessageByKey("reservable_room_delete")));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
