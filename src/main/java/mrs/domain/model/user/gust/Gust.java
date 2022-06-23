package mrs.domain.model.user.gust;

import mrs.domain.model.auth.user.*;
import mrs.domain.model.contact.ContactPerson;

/**
 * ゲスト
 */
public class Gust extends User implements ContactPerson {
    private User user;

    private UserId userId;

    private Password password;

    private UserName name;

    private RoleName roleName;

    @Deprecated
    public Gust() {
    }

    public Gust(UserName name) {
        this.user = new User("U999999", name.FirstName(), name.LastName(), "", RoleName.ゲスト);
        this.userId = user.UserId();
        this.password = user.Password();
        this.name = user.Name();
        this.roleName = user.RoleName();
    }

    @Override
    public UserId UserId() {
        return this.user.UserId();
    }

    @Override
    public Password Password() {
        return this.user.Password();
    }

    @Override
    public UserName Name() {
        return this.user.Name();
    }

    @Override
    public RoleName RoleName() {
        return this.user.RoleName();
    }

}
