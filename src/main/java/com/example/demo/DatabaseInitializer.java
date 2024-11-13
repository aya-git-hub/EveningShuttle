package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * This is the project initializer to make sure the ETA are NULL when running in the first.
 */

// Indicate that a class is a Spring-managed bean.
// It tells the Spring container to create an instance of the class and manage its lifecycle, enabling dependency injection.
 @Component
public class DatabaseInitializer {

    private final StudentRepository studentRepository;

    public DatabaseInitializer(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Bean// Indicate that a method produces a bean to be managed by the Spring container
    public CommandLineRunner initializeDatabase() {
        return args -> {
            // Update all the ETA to NULL.
            studentRepository.resetEtaValues();
            System.out.println("Database initialized: ETA values have been updated to NULL where applicable.");
        };
    }
}
