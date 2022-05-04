package com.sofka.todoAPI.service;


import com.sofka.todoAPI.model.TodoModel;
import com.sofka.todoAPI.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    /**
     * Get all the todos from the database and return them as a list of TodoModel objects.
     *
     * @return A list of all the todos in the database.
     */
    public List<TodoModel> getAllTodos() {
        return todoRepository.findAll();
    }

    /**
     * Save the todo object to the database.
     *
     * @param todo The todo object that we want to save.
     * @return The todo object that was saved.
     */
    public TodoModel saveTodo(TodoModel todo) {
        return todoRepository.save(todo);
    }

    /**
     * If the todo with the given id exists, return it, otherwise return null.
     *
     * @param id The id of the todo to retrieve
     * @return Optional<TodoModel>
     */
    public Optional<TodoModel> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    /**
     * > It deletes a todo item from the database
     *
     * @param id The id of the todo item to be deleted.
     * @return A boolean value.
     */
    public boolean deteleTodo(Long id) {
        try {
            todoRepository.deleteById(id);
            return true;
        } catch (Exception err) {
            return false;
        }
    }
}
