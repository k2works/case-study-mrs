package mrs.presentation.api.reservation;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mrs.application.scenario.MeetingRoomReservationScenario;
import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomList;
import mrs.infrastructure.PageNation;
import mrs.infrastructure.security.jwt.payload.response.MessageResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * 会議室一覧API
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rooms")
@Tag(name = "会議室一覧API", description = "会議室一覧API")
@PreAuthorize("hasRole('一般') or hasRole('管理者')")
public class RoomsApiController {
    final MeetingRoomReservationScenario meetingRoomReservationScenario;

    public RoomsApiController(MeetingRoomReservationScenario meetingRoomReservationScenario) {
        this.meetingRoomReservationScenario = meetingRoomReservationScenario;
    }

    @Operation(summary = "会議室一覧の取得", description = "当日の会議室一覧を取得する")
    @GetMapping
    ResponseEntity<?> listRooms(@RequestParam(value = "page", defaultValue = "1") int... page) {
        try {
            PageNation.startPage(page);
            ReservableRoomList rooms = meetingRoomReservationScenario.findReservableRooms(new ReservedDate(LocalDate.now())).orElse(new ReservableRoomList());
            PageInfo<ReservableRoom> pageInfo = new PageInfo<>(rooms.asList());
            return ResponseEntity.ok(pageInfo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }

    @Operation(summary = "会議室一覧の取得", description = "指定日の会議室一覧を取得する")
    @GetMapping("{date}")
    ResponseEntity<?> listRooms(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @PathVariable("date") LocalDate date,
            @RequestParam(value = "page", defaultValue = "1") int... page
    ) {
        try {
            PageNation.startPage(page);
            ReservableRoomList rooms = meetingRoomReservationScenario.findReservableRooms(new ReservedDate(date)).orElse(new ReservableRoomList());
            PageInfo<ReservableRoom> pageInfo = new PageInfo<>(rooms.asList());
            return ResponseEntity.ok(pageInfo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageResponse(e.getMessage()));
        }
    }
}
