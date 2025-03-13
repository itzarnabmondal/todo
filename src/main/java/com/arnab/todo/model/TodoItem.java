package com.arnab.todo.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Class representing a to-do item.
 */
@Entity
@Table(name = "todo_item")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private boolean complete;

    @Getter
    @Setter
    private Instant createdDate;

    @Getter
    @Setter
    private Instant modifiedDate;

    /**
     * Constructs a new {@code TodoItem} with the given description.
     * 
     * @param description the description of the to-do item
     */
    public TodoItem(String description) {
        this.description = description;
        this.complete = false;
        this.createdDate = Instant.now();
        this.modifiedDate = Instant.now();
    }

    /**
     * Constructs a new {@code TodoItem} with no arguments.
     * This constructor is required by the JPA.
     */
    public TodoItem() {
    }

    /**
     * Returns a string representation of this {@code TodoItem}.
     * 
     * @return a string representation of this {@code TodoItem}
     */
    @Override
    public String toString() {
        return "TodoItem{id=%d, description='%s', complete='%s', createdDate='%s', modifiedDate='%s'}".formatted(
                id, description, complete, createdDate, modifiedDate);
    }

}
