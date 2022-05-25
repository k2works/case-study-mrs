package mrs.infrastructure.datasource.user;

import mrs.domain.model.user.User;
import mrs.domain.model.user.UserId;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void insert(User todo);

    User select(UserId userId);

    List<User> selectAllJoin();

    void update(User record);

    void delete(UserId id);

    void deleteAll();
}
