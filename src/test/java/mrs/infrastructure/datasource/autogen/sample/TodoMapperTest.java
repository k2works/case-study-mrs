package mrs.infrastructure.datasource.autogen.sample;

import mrs.domain.model.autogen.sample.Todo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MybatisTest
public class TodoMapperTest {
    @Qualifier("todoMapperAutogen")
    @Autowired
    TodoMapper todoMapper;

    @Test
    void やることが登録される() throws Exception {
        Todo newTodo = new Todo();
        newTodo.setTitle("飲み会");
        newTodo.setDetails("銀座 19:00");
        newTodo.setFinished(false);
        todoMapper.insert(newTodo);

        Todo result = todoMapper.selectByPrimaryKey(1);
        assertEquals("飲み会", result.getTitle());
        assertEquals("銀座 19:00", result.getDetails());
    }
}
