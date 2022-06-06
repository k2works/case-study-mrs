package mrs.infrastructure.datasource.auth;

import mrs.domain.model.auth.user.User;
import mrs.domain.model.auth.user.UserId;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void insert(User user);

    User select(UserId userId);

    List<User> selectAll();

    void update(User user);

    void delete(UserId id);

    void deleteAll();
}
