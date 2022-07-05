package mrs.presentation.api.reservation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mrs.application.scenario.MeetingRoomReservationScenario;
import mrs.domain.model.auth.user.UserDetailsImpl;
import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.reservation.ReservationId;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import org.springframework.format.annotation.DateTimeFormat;
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

    public ReservationsApiController(MeetingRoomReservationScenario meetingRoomReservationScenario) {
        this.meetingRoomReservationScenario = meetingRoomReservationScenario;
    }

    @Operation(summary = "会議室の予約", description = "選択した会議室を予約する")
    @PostMapping
    void reserve(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
            @PathVariable("roomId") Integer roomId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) @RequestParam(value = "start", required = true) LocalTime start,
            @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) @RequestParam(value = "end", required = true) LocalTime end
    ) {
        ReservableRoom reservableRoom = new ReservableRoom(new ReservableRoomId(roomId, date));
        Reservation reservation = new Reservation(null, start, end, reservableRoom.ReservableRoomId(), userDetails.getUser());
        meetingRoomReservationScenario.reserve(reservation);
    }

    @Operation(summary = "会議室の取消", description = "選択した会議室の予約をキャンセルする")
    @DeleteMapping
    void cancel(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable("roomId") Integer roomId,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
            @RequestParam(value = "reservationId", required = true) Integer reservationId
    ) {
        Reservation reservation = meetingRoomReservationScenario.findOne(new ReservationId(reservationId));
        meetingRoomReservationScenario.cancel(reservation);
    }
}
