package com.arnab.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * TodoApplication is the main class for the Spring Boot application.
 * It is used to bootstrap and launch the application from the command line.
 */
@SpringBootApplication
public class TodoApplication {

    /**
     * The main method is the entry point for the application.
     * It uses Spring Boot's SpringApplication.run method to launch the application.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

}
