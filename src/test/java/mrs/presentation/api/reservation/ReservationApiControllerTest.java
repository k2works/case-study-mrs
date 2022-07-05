package mrs.presentation.api.reservation;

import mrs.application.scenario.MeetingRoomReservationScenario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("会議室予約API")
public class ReservationApiControllerTest {
    MockMvc mockMvc;

    @InjectMocks
    ReservationsApiController controller;

    @Mock
    MeetingRoomReservationScenario mockMeetingRoomReservationScenario;

    @BeforeEach
    void setUpMockMvc() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    //TODO
    @Disabled("成功しない")
    void 予約可能な会議室を検索する() throws Exception {
        given(mockMeetingRoomReservationScenario.findReservableRooms(any())).willReturn(null);

        mockMvc.perform(
                get("/api/reservations/2021-01-01/1")
        ).andExpect(status().isNotAcceptable());
    }

    @Test
    void 会議室を予約する() throws Exception {
        doNothing().when(mockMeetingRoomReservationScenario).reserve(null);

        mockMvc.perform(
                post("/api/reservations/2021-01-01/1")
                        .param("start", String.valueOf(LocalTime.of(10, 0)))
                        .param("end", String.valueOf(LocalTime.of(10, 30)))
        ).andExpect(status().isOk());

    }

    @Test
    void 会議室の予約をキャンセルする() throws Exception {
        doNothing().when(mockMeetingRoomReservationScenario).cancel(null);

        mockMvc.perform(
                delete("/api/reservations/2021-01-01/1")
                        .param("reservationId", "1")
        ).andExpect(status().isOk());
    }
}
