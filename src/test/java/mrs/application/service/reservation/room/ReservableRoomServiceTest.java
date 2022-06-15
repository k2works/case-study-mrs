package mrs.application.service.reservation.room;

import mrs.IntegrationTest;
import mrs.TestDataFactory;
import mrs.application.service.facility.room.MeetingRoomService;
import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.domain.model.reservation.room.ReservableRoomList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@IntegrationTest
@DisplayName("予約サービス")
public class ReservableRoomServiceTest {
    @Autowired
    ReservableRoomService reservableRoomService;

    @Autowired
    MeetingRoomService meetingRoomService;

    @Autowired
    TestDataFactory testDataFactory;

    @BeforeEach
    void setUp() {
        testDataFactory.setUp();
    }

    @Nested
    class 会議室の検索 {
        @Test
        void 予約可能な会議室を検索する() {
            LocalDate date = LocalDate.of(2020, 1, 1);
            ReservableRoomList rooms = reservableRoomService.findReservableRooms(new ReservedDate(date)).orElse(new ReservableRoomList());

            assertEquals(3, rooms.size());
        }
    }

    @Nested
    class 会議室の管理 {
        @Test
        void 利用可能な会議室を取得する() {
            List<ReservableRoom> rooms = reservableRoomService.findAllReservableRooms();

            assertEquals(3, rooms.size());
        }

        @Test
        void 利用可能な会議室を登録する() {
            ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2022, 1, 1));
            reservableRoomService.registReservableRoom(reservableRoomId);

            ReservableRoom result = reservableRoomService.findReservableRoomById(reservableRoomId);
            assertEquals(reservableRoomId, result.ReservableRoomId());
        }

        @Nested
        class 利用可能な会議室を削除する {
            @Test
            void 新規登録した利用可能な会議室を削除する() {
                ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2022, 1, 1));
                reservableRoomService.registReservableRoom(reservableRoomId);
                reservableRoomService.deleteReservableRoom(reservableRoomId);
                ReservableRoom result = reservableRoomService.findReservableRoomById(reservableRoomId);

                assertNull(result);
            }

            @Test
            void 予約済みの会議室は削除できない() {
                ReservableRoomId reservableRoomId = new ReservableRoomId(1, LocalDate.of(2020, 1, 1));
                assertThrows(ReservableRoomAlreadyReservedException.class, () -> reservableRoomService.deleteReservableRoom(reservableRoomId));
            }
        }
    }
}
