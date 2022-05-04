package com.sofka.todoAPI.ServicesTest;

import com.sofka.todoAPI.model.TodoModel;
import com.sofka.todoAPI.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TodoServiceTest {
    @Autowired
    TodoRepository todoRepository;

    @Test
    public void testSaveTodo(){
        TodoModel testTodoModel = new TodoModel(1L, "Cortar cebolla para el almuerzo", false);
        TodoModel todoSaved = todoRepository.save(testTodoModel);
        assertNotNull(todoSaved);
    }

    @Test
    public void testFindTodoById() {
        Long searchedId = 1L;
        Optional<TodoModel> searchedTodoItem = todoRepository.findById(searchedId);
        assertEquals(searchedTodoItem.get().getId(), searchedId, "El id debe ser 1");
    }

    @Test
    public void testGetTextTodo(){
        TodoModel testTodoModel = new TodoModel(1L, "Hola mundo", false);
        TodoModel todoSaved = todoRepository.save(testTodoModel);
        assertEquals(todoRepository.getById(1L).getText(), "Hola mundo", "El texto debe ser 'Hola mundo'");
    }

}
