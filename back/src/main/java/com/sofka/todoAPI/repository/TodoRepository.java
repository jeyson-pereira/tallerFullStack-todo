package com.sofka.todoAPI.repository;

import com.sofka.todoAPI.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<TodoModel, Long> {

}
