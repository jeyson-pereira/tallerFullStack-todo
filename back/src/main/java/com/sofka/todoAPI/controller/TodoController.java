package com.sofka.todoAPI.controller;

import com.sofka.todoAPI.model.TodoModel;
import com.sofka.todoAPI.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    TodoService todoService;

    /**
     * It returns a list of all todos in the database
     *
     * @return A list of TodoModel objects.
     */
    @GetMapping("/todos")
    public ResponseEntity<List<TodoModel>> getAllTodos() {
        List<TodoModel> getAllTodos = todoService.getAllTodos();
        try {
            return new ResponseEntity<>(getAllTodos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It takes a TodoModel object as a parameter, saves it to the database, and returns a ResponseEntity with the saved
     * TodoModel object and a status code of 201 (CREATED)
     *
     * @param todo The todo object that will be saved in the database.
     * @return A ResponseEntity object is being returned.
     */
    @PostMapping("/todos")
    public ResponseEntity<TodoModel> saveTodo(@RequestBody TodoModel todo) {
        return new ResponseEntity<>(todoService.saveTodo(todo), HttpStatus.CREATED);
    }

    /**
     * If the todo with the given id exists, return it, otherwise return a 404
     *
     * @param id The id of the todo we want to retrieve.
     * @return A ResponseEntity object is being returned.
     */
    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable("id") Long id) {
        Optional<TodoModel> getSingleTodo = todoService.getTodoById(id);
        if (getSingleTodo.isPresent()) {
            return new ResponseEntity<>(getSingleTodo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * If the todo exists, delete it and return a 204 status code. If the todo doesn't exist, return a 404 status code
     *
     * @param id The id of the todo item to be deleted.
     * @return A ResponseEntity is being returned.
     */
    @DeleteMapping("/todos/{id}")
    public ResponseEntity<?> deleteTodoById(@PathVariable("id") Long id) {
        Optional<TodoModel> singleTodo = todoService.getTodoById(id);
        if (singleTodo.isPresent()) {
            todoService.deteleTodo(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/todos/{id}/completed")
    public ResponseEntity<?> updateCompletedFromTodo(@PathVariable("id") Long id, @RequestBody TodoModel todo) {
        Optional<TodoModel> singleTodo = todoService.getTodoById(id);
        if (singleTodo.isPresent()) {
            todoService.updateCompleted(id,todo);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
