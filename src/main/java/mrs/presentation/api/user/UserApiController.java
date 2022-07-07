package mrs.presentation.api.user;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mrs.application.service.user.UserManagementService;
import mrs.domain.model.auth.user.RoleName;
import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;
import mrs.infrastructure.PageNation;
import mrs.infrastructure.datasource.Message;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.util.List;

/**
 * 利用者API
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
@Tag(name = "利用者API", description = "利用者API")
@PreAuthorize("hasRole('管理者')")
public class UserApiController {
    final UserManagementService userManagementService;

    final Message message;

    public UserApiController(UserManagementService userManagementService, Message message) {
        this.userManagementService = userManagementService;
        this.message = message;
    }

    @Operation(summary = "利用者一覧の取得", description = "利用者一覧を取得する")
    @GetMapping
    PageInfo<User> list(Model model, @RequestParam(value = "page", defaultValue = "1") int... page) {
        PageNation.startPage(page);
        List<User> users = userManagementService.findAll();
        return new PageInfo<>(users);
    }

    @Operation(summary = "利用者の登録", description = "利用者を登録する")
    @PostMapping
    User create(@RequestBody @Validated UserResource resource) {
        User user = new User(resource.getUserId(), resource.getFirstName(), resource.getLastName(), resource.getPassword(), resource.getRoleName());
        userManagementService.regist(user);
        return user;
    }

    @Operation(summary = "利用者の更新", description = "利用者を更新する")
    @PutMapping("/{userId}")
    User update(@PathVariable String userId, @RequestBody @Validated UserResource resource) {
        User user = new User(userId, resource.getFirstName(), resource.getLastName(), resource.getPassword(), resource.getRoleName());
        userManagementService.update(user);
        return user;
    }

    @Operation(summary = "利用者の削除", description = "利用者を削除する")
    @DeleteMapping("/{userId}")
    void delete(
            Principal principal,
            @PathVariable("userId") String id
    ) throws AccessDeniedException {
        if (id.equals(principal.getName()))
            throw new AccessDeniedException(message.getMessageByKey("user_delete_exception"));
        UserId userId = new UserId(id);
        User user = this.userManagementService.findOne(userId);
        if (user.getReservations().size() > 0)
            throw new AccessDeniedException(message.getMessageByKey("user_reserved_delete_exception"));

        userManagementService.delete(userId);
    }

    @Operation(summary = "役割一覧", description = "役割一覧を取得する")
    @GetMapping("/roleNames")
    RoleName[] roleNames() {
        return RoleName.values();
    }
}
