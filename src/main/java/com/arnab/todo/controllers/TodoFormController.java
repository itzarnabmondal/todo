package com.arnab.todo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.arnab.todo.model.TodoItem;
import com.arnab.todo.repository.TodoItemRepository;

/**
 * TodoFormController is a Spring MVC controller responsible for handling
 * requests
 * related to creating, updating, and deleting TodoItem objects.
 * 
 * @author Arnab Mondal
 */
@Controller
public class TodoFormController {

    /**
     * The todoItemRepository is used to access TodoItem objects in the database.
     */
    @Autowired
    private TodoItemRepository todoItemRepository;

    /**
     * showCreateForm is a method that handles GET requests to /create-todo.
     * It displays a form for creating a new TodoItem.
     *
     * @param todoItem a TodoItem object used to bind form data
     * @return the name of the template to render
     */
    @GetMapping("/create-todo")
    public String showCreateForm(TodoItem todoItem) {
        return "add-todo-item";
    }

    /**
     * showUpdateForm is a method that handles GET requests to /edit/{id}.
     * It displays a form for updating an existing TodoItem.
     *
     * @param id    the id of the TodoItem to edit
     * @param model the Spring MVC model
     * @return the name of the template to render
     */
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        TodoItem todoItem = todoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem not found"));

        model.addAttribute("todo", todoItem);
        return "update-todo-item";
    }

    /**
     * deleteTodoItem is a method that handles GET requests to /delete/{id}.
     * It deletes the TodoItem with the specified id.
     *
     * @param id    the id of the TodoItem to delete
     * @param model the Spring MVC model
     * @return a redirect to the root path
     */
    @GetMapping("/delete/{id}")
    public String deleteTodoItem(@PathVariable("id") long id, Model model) {
        TodoItem todoItem = todoItemRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem not found"));

        todoItemRepository.delete(todoItem);
        return "redirect:/";
    }

}
