package com.sofka.todoAPI.repository;

import com.sofka.todoAPI.model.TodoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TodoRepository extends JpaRepository<TodoModel, Long> {
    @Modifying
    @Query(value = "update TodoModel todo set todo.completed = :completed where todo.id = :id")
    public void updateCompleted(@Param(value = "id") Long id, @Param(value = "completed") Boolean completed);
}
