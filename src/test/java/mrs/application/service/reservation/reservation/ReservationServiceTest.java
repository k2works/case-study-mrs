package mrs.application.service.reservation.reservation;

import mrs.IntegrationTest;
import mrs.TestDataFactory;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;
import mrs.domain.model.reservation.reservation.Reservation;
import mrs.domain.model.reservation.reservation.ReservationId;
import mrs.domain.model.reservation.reservation.ReservationList;
import mrs.domain.model.reservation.room.ReservableRoomId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


@IntegrationTest
@DisplayName("予約サービス")
public class ReservationServiceTest {
    @Autowired
    ReservationService reservationService;
    @Autowired
    TestDataFactory testDataFactory;

    @Nested
    class 会議室の予約 {
        @BeforeEach
        void setUp() {
            testDataFactory.setUp();
        }

        @Test
        @WithMockUser
        void 会議室の予約を検索する() {
            ReservationId reservationId = new ReservationId(1);
            Reservation result = reservationService.findOne(reservationId);

            assertEquals(reservationId, result.ReservationId());
            assertEquals(LocalTime.of(10, 0), result.ReservedTime().EndTime());
            assertEquals(LocalTime.of(9, 0), result.ReservedTime().StartTime());
            assertEquals(LocalDate.of(2020, 1, 1), result.ReservableRoom().ReservableRoomId().ReservedDate().Value());
            assertEquals("会議室A", result.ReservableRoom().MeetingRoom().RoomName());
            assertEquals("U999999", result.User().UserId().Value());
        }

        @Test
        @WithMockUser
        void 会議室の予約一覧を取得する() {
            ReservableRoomId reservableRoomId = testDataFactory.getReservableRoomId();

            ReservationList result = reservationService.findReservations(reservableRoomId);

            assertEquals(1, result.size());
        }

        @Test
        @WithMockUser(username = "cccc", roles = "管理者")
        void 会議室の予約を取り消す() {
            ReservationId reservationId = new ReservationId(1);
            Reservation reservation = reservationService.findOne(reservationId);
            reservationService.cancel(reservation);

            Reservation result = reservationService.findOne(reservationId);
            assertNull(result);
        }

        @Nested
        class 会議室を予約する {
            @Test
            @WithMockUser
            public void 予約時刻が重複しなければ登録できる() {
                UserId userId = new UserId("U999999");
                User user = testDataFactory.getUserMapper().select(userId);
                ReservableRoomId reservableRoomId = testDataFactory.getReservableRoomId();
                Reservation reservation = new Reservation(2, LocalTime.of(10, 0), LocalTime.of(11, 0), reservableRoomId, user);
                reservationService.reserve(reservation);

                ReservationList result = reservationService.findReservations(reservableRoomId);

                assertEquals(2, result.size());
            }

            @Test
            public void 該当する予約された会議室が存在しなければ例外メッセージを表示する() {
                UserId userId = new UserId("U999999");
                User user = testDataFactory.getUserMapper().select(userId);
                ReservableRoomId reservableRoomId = new ReservableRoomId(9, LocalDate.of(2020, 1, 1));
                Reservation reservation = new Reservation(2, LocalTime.of(10, 0), LocalTime.of(11, 0), reservableRoomId, user);

                Throwable result = assertThrows(UnavailableReservationException.class, () -> {
                    reservationService.reserve(reservation);
                });
                assertEquals("入力の日付・部屋の組み合わせは予約できません。", result.getMessage());
            }

            @Test
            @WithMockUser
            public void 予約時刻が重複すれば登録できない_パターン1() {
                UserId userId = new UserId("U999999");
                User user = testDataFactory.getUserMapper().select(userId);
                ReservableRoomId reservableRoomId = testDataFactory.getReservableRoomId();
                Reservation reservation = new Reservation(2, LocalTime.of(10, 0), LocalTime.of(11, 0), reservableRoomId, user);
                reservationService.reserve(reservation);

                Throwable result = assertThrows(AlreadyReservedException.class, () -> {
                    reservationService.reserve(new Reservation(3, LocalTime.of(10, 30), LocalTime.of(11, 30), reservableRoomId, user));
                });
                assertEquals("入力の時間帯はすでに予約済です。", result.getMessage());
            }

            @Test
            @WithMockUser
            public void 予約時刻が重複すれば登録できない_パターン2() {
                UserId userId = new UserId("U999999");
                User user = testDataFactory.getUserMapper().select(userId);
                ReservableRoomId reservableRoomId = testDataFactory.getReservableRoomId();
                Reservation reservation = new Reservation(2, LocalTime.of(10, 0), LocalTime.of(13, 0), reservableRoomId, user);
                reservationService.reserve(reservation);

                Throwable result = assertThrows(AlreadyReservedException.class, () -> {
                    reservationService.reserve(new Reservation(3, LocalTime.of(11, 0), LocalTime.of(12, 0), reservableRoomId, user));
                });
                assertEquals("入力の時間帯はすでに予約済です。", result.getMessage());
            }
        }
    }
}
