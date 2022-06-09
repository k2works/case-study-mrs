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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .andExpect(status().isOk())
                .andExpect(content().string(""))
                .andExpect(forwardedUrl("user/userList"));
    }

    @Test
    void 利用者を登録する() throws Exception {
        mockMvc.perform(post("/users").param("regist", ""))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("user/userList"));
    }

    @Test
    void 利用者を更新する() throws Exception {
        mockMvc.perform(post("/users").param("update", ""))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("user/userList"));
    }

    @Test
    void 利用者を削除する() throws Exception {
        mockMvc.perform(get("/users/delete/1"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("user/userList"));
    }
}
