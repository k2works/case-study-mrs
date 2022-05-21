package mrs.infrastructure.datasource;

import mrs.domain.model.todo.Todo;
import mrs.infrastructure.datasource.todo.TodoDataSource;
import mrs.infrastructure.datasource.todo.TodoMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TodoDataSourceTest {
    @Autowired
    TodoMapper todoMapper;

    @Autowired
    TodoDataSource todoDataSource;

    @Test
    void 全てのやることを関連テーブルも含めて取得できる() throws Exception {
        Todo newTodo = new Todo(
                "飲み会",
                "銀座 19:00",
                false
        );
        todoMapper.insert(newTodo);

        newTodo = new Todo(
                "飲み会2",
                "銀座 19:00",
                false
        );
        todoMapper.insert(newTodo);

        List<Todo> result = todoDataSource.selectAllJoin();

        assertEquals(2, result.size());
    }
}
