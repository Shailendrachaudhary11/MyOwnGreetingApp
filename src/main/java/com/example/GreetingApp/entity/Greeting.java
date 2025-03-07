package com.example.GreetingApp.entity;

import jakarta.persistence.*;

@Entity
public class Greeting {

    @Id // this is a primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generates an ID.
    private Long id;

    private String message;

    public Greeting() {}

    public Greeting(String message) {
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
