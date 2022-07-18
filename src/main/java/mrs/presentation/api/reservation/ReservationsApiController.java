package mrs.presentation.api.reservation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mrs.application.scenario.MeetingRoomReservationScenario;
import mrs.domain.model.auth.user.UserDetailsImpl;
import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.reservation.ReservationId;
import mrs.domain.model.reservation.reservation.ReservationList;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.infrastructure.PageNation;
import mrs.infrastructure.datasource.Message;
import mrs.infrastructure.security.jwt.payload.response.MessageResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 会議室予約API
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reservations/{date}/{roomId}")
@Tag(name = "会議室予約API", description = "会議室予約API")
@PreAuthorize("hasRole('一般') or hasRole('管理者')")
public class ReservationsApiController {
    private final MeetingRoomReservationScenario meetingRoomReservationScenario;
    final Message message;

    public ReservationsApiController(MeetingRoomReservationScenario meetingRoomReservationScenario, Message message) {
        this.meetingRoomReservationScenario = meetingRoomReservationScenario;
        this.message = message;
    }

    @Operation(summary = "予約一覧", description = "予約一覧を取得する")
    @GetMapping
    ResponseEntity<?> findReservations(
            @PathVariable("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @PathVariable("roomId") Integer roomId,
            @RequestParam(value = "page", defaultValue = "1") int... page
    ) {
        try {
            PageNation.startPage(page);
            ReservableRoomId reservableRoomId = new ReservableRoomId(roomId, date);
            ReservationList result = meetingRoomReservationScenario.findReservations(reservableRoomId).orElse(new ReservationList());
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @Operation(summary = "会議室の予約", description = "選択した会議室を予約する")
    @PostMapping
    ResponseEntity<?> reserve(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
            @PathVariable("roomId") Integer roomId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) @RequestParam(value = "start", required = true) LocalTime start,
            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) @RequestParam(value = "end", required = true) LocalTime end
    ) {
        try {
            ReservableRoom reservableRoom = new ReservableRoom(new ReservableRoomId(roomId, date));
            Reservation reservation = new Reservation(null, start, end, reservableRoom.ReservableRoomId(), userDetails.getUser());
            meetingRoomReservationScenario.reserve(reservation);
            return ResponseEntity.ok(new MessageResponse(message.getMessageByKey("reserve_success")));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @Operation(summary = "会議室の取消", description = "選択した会議室の予約をキャンセルする")
    @DeleteMapping
    ResponseEntity<?> cancel(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("roomId") Integer roomId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
            @RequestParam(value = "reservationId", required = true) Integer reservationId
    ) {
        try {
            Reservation reservation = meetingRoomReservationScenario.findOne(new ReservationId(reservationId));
            meetingRoomReservationScenario.cancel(reservation);
            return ResponseEntity.ok(new MessageResponse(message.getMessageByKey("reserve_cancel")));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
