package mrs.presentation.api.contact;

import com.github.pagehelper.PageInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mrs.application.scenario.ContactManagementScenario;
import mrs.domain.model.auth.user.UserDetailsImpl;
import mrs.domain.model.contact.Contact;
import mrs.domain.model.contact.ContactList;
import mrs.domain.model.user.gust.Gust;
import mrs.domain.model.user.member.Member;
import mrs.infrastructure.PageNation;
import mrs.infrastructure.datasource.Message;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 問い合わせAPI
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/contacts")
@Tag(name = "問い合わせAPI", description = "問い合わせAPI")
public class ContactRegistApiController {
    final ContactManagementScenario contactManagementScenario;
    final Message message;

    public ContactRegistApiController(ContactManagementScenario contactManagementScenario, Message message) {
        this.contactManagementScenario = contactManagementScenario;
        this.message = message;
    }

    @PreAuthorize("hasRole('管理者')")
    @Operation(summary = "問い合わせ一覧", description = "問い合わせ一覧")
    @GetMapping
    public PageInfo<Contact> list(@RequestParam(value = "page", defaultValue = "1") int... page) {
        PageNation.startPage(page);
        ContactList contacts = contactManagementScenario.findAll();
        return new PageInfo<>(contacts.asList());
    }

    @PreAuthorize("hasRole('一般') or hasRole('管理者')")
    @Operation(summary = "問い合わせの登録", description = "会員の問い合わせを登録する")
    @PostMapping
    void createContact(
            @RequestBody @Validated ContactResource resource,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        Contact contact = new Contact(resource.getDetails(), new Member(userDetails.getUser()));
        contactManagementScenario.create(contact);
    }

    @Operation(summary = "問い合わせの登録", description = "ゲストの問い合わせを登録する")
    @PostMapping("/guest")
    void createGuestContact(
            @RequestBody @Validated ContactResource resource
    ) {
        Contact contact = new Contact(resource.getDetails(), new Gust());
        contactManagementScenario.create(contact);
    }
}
