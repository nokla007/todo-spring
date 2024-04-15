package com.nokla.todo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "todos")
@Getter
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Setter
    private String description;
    @Setter
    private Boolean completed;
    @Setter
    private Boolean starred;

    public Todo(String description) {
        this.description = description;
        this.starred = false;
        this.completed = false;
    }

    public Todo(String description, boolean starred) {
        this.description = description;
        this.starred = starred;
        this.completed = false;
    }

    public Todo(String description, boolean starred, boolean completed) {
        this.description = description;
        this.starred = starred;
        this.completed = completed;
    }

    public Todo() {
    }
}

