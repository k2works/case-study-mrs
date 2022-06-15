package mrs.application.service.room;

import mrs.IntegrationTest;
import mrs.TestDataFactory;
import mrs.application.service.auth.UserAlreadyRegistException;
import mrs.domain.model.property.room.MeetingRoom;
import mrs.domain.model.property.room.RoomId;
import mrs.domain.model.reservation.reservation.ReservedDate;
import mrs.domain.model.reservation.room.ReservableRoom;
import mrs.domain.model.reservation.room.ReservableRoomId;
import mrs.domain.model.reservation.room.ReservableRoomList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@IntegrationTest
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
        void 会議室を検索する() {
            RoomId roomId = new RoomId(1);
            MeetingRoom room = meetingRoomService.findMeetingRoom(roomId);

            assertEquals(1, room.RoomId().Value());
            assertEquals("会議室A", room.RoomName());
        }

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
        void 会議室一覧を取得する() {
            List<MeetingRoom> rooms = meetingRoomService.findAll();

            assertEquals(3, rooms.size());
        }

        @Test
        void 会議室を更新する() {
            MeetingRoom room = new MeetingRoom(4, "会議室A");
            meetingRoomService.registMeetingRoom(room);
            MeetingRoom registRoom = meetingRoomService.findMeetingRoom(new RoomId(4));
            MeetingRoom updateRoom = new MeetingRoom(registRoom.RoomId().Value(), "会議室B");
            meetingRoomService.updateMeetingRoom(updateRoom);
            MeetingRoom result = meetingRoomService.findMeetingRoom(new RoomId(4));

            assertEquals(4, result.RoomId().Value());
            assertEquals("会議室B", result.RoomName());
        }

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
        class 会議室を登録する {
            @Test
            void 会議室を新規登録する() {
                MeetingRoom room = new MeetingRoom(4, "会議室A");
                meetingRoomService.registMeetingRoom(room);

                MeetingRoom result = meetingRoomService.findMeetingRoom(new RoomId(4));
                assertEquals(4, result.RoomId().Value());
                assertEquals("会議室A", result.RoomName());
            }

            @Test
            void 登録済みの会議室は新規登録できない() {
                MeetingRoom room = new MeetingRoom(1, "会議室A");

                assertThrows(UserAlreadyRegistException.class, () -> meetingRoomService.registMeetingRoom(room));
            }
        }

        @Nested
        class 会議室を削除する {
            @Test
            void 新規登録した会議室を削除する() {
                MeetingRoom room = new MeetingRoom(4, "会議室A");
                meetingRoomService.registMeetingRoom(room);
                meetingRoomService.deleteMeetingRoom(4);
                MeetingRoom result = meetingRoomService.findMeetingRoom(new RoomId(4));

                assertNull(result);
            }

            @Test
            void 予約可能会議室は削除できない() {
                assertThrows(MeetingRoomAlreadyUsedException.class, () -> meetingRoomService.deleteMeetingRoom(1));
            }
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
