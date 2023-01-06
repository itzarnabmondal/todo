package com.arnab.todo.controllers;

import java.time.Instant;
import java.time.ZoneId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.arnab.todo.model.TodoItem;
import com.arnab.todo.repository.TodoItemRepository;

import jakarta.validation.Valid;

/**
 * TodoItemController is a Spring MVC controller responsible for handling
 * requests
 * related to displaying and managing TodoItem objects.
 */
@Controller
public class TodoItemController {
    private final Logger logger = LoggerFactory.getLogger(TodoItemController.class);

    /**
     * The todoItemRepository is used to access TodoItem objects in the database.
     */
    @Autowired
    private TodoItemRepository todoItemRepository;

    /**
     * index is a method that handles GET requests to the root path (/).
     * It displays a list of TodoItems and the current day of the week.
     *
     * @return a ModelAndView object containing the TodoItem list and the current
     *         day of the week
     */
    @GetMapping("/")
    public ModelAndView index() {

        logger.info("request to GET index");
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("todoItems", todoItemRepository.findAll());
        modelAndView.addObject("today", Instant.now()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .getDayOfWeek());

        return modelAndView;
    }

    /**
     * createTodoItem is a method that handles POST requests to /todo.
     * It creates a new TodoItem in the database.
     *
     * @param todoItem a TodoItem object containing the form data
     * @param result   the BindingResult object containing validation errors
     * @param model    the Spring MVC model
     * @return the name of the template to render or a redirect to the root path
     */
    @PostMapping("/todo")
    public String createTodoItem(@Valid TodoItem todoItem, BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "add-todo-item";
        }

        todoItem.setCreatedDate(Instant.now());
        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }

    /**
     * updateTodoItem is a method that handles POST requests to /todo/{id}.
     * It updates an existing TodoItem in the database.
     *
     * @param id       the id of the TodoItem to update
     * @param todoItem a TodoItem object containing the form data
     * @param result   the BindingResult object containing validation errors
     * @param model    the Spring MVC model
     * @return the name of the template to render or a redirect to the root path
     */

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") long id, @Valid TodoItem todoItem, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            todoItem.setId(id);
            return "update-todo-item";
        }

        todoItem.setModifiedDate(Instant.now());
        todoItemRepository.save(todoItem);
        return "redirect:/";
    }

}