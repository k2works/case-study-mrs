package mrs.infrastructure.datasource.service_user;

import mrs.domain.model.service_user.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    void insert(User todo);

    User select(String userId);

    List<User> selectAllJoin();

    void update(User record);

    void delete(String id);

    void deleteAll();
}
