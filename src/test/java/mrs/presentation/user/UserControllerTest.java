package mrs.presentation.user;

import mrs.application.service.auth.UserManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class UserControllerTest {
    MockMvc mockMvc;

    @InjectMocks
    UserController controller;

    @Mock
    UserManagementService mockUserManagementService;

    @BeforeEach
    public void setUpMockMvc() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void 利用者一覧を表示する() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }

    @Test
    void 利用者を登録する() throws Exception {
        mockMvc.perform(post("/users").param("name", "regist"))
                .andExpect(status().isOk());
    }
}
