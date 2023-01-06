package com.arnab.todo.repository;

import org.springframework.data.repository.CrudRepository;

import com.arnab.todo.model.TodoItem;

/**
 * Repository interface for {@link TodoItem}s.
 */
public interface TodoItemRepository extends CrudRepository<TodoItem, Long> {

}
