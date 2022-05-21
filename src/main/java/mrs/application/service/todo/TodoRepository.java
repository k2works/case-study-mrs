package mrs.application.service.todo;

import mrs.domain.model.todo.Todo;

import java.util.List;

/**
 * やることレポジトリ
 */
public interface TodoRepository {
    List<Todo> selectAllJoin();
}
