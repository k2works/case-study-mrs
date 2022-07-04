package mrs.presentation.api.room;

import mrs.application.scenario.MeetingRoomReservationScenario;
import mrs.domain.model.reservation.room.ReservableRoomList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("API 会議室一覧")
public class RoomsApiControllerTest {
    MockMvc mockMvc;

    @InjectMocks
    RoomsApiController controller;

    @Mock
    MeetingRoomReservationScenario mockMeetingRoomReservationScenario;

    @BeforeEach
    void setUpMockMvc() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void 会議室一覧を取得する() throws Exception {
        given(mockMeetingRoomReservationScenario.findReservableRooms(any())).willReturn(Optional.of(new ReservableRoomList()));

        mockMvc.perform(get("/api/rooms")
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    void 日付を指定して会議室一覧を取得する() throws Exception {
        given(mockMeetingRoomReservationScenario.findReservableRooms(any())).willReturn(Optional.of(new ReservableRoomList()));

        mockMvc.perform(
                get("/api/rooms")
                        .param("date", "20200-01-01")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
