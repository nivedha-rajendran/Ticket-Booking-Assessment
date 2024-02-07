package com.assessment.ticket.booking;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * The main class for the Ticket Booking Application.
 */
@SpringBootApplication
public class Application {

    /**
     * The main method to start the Spring Boot application.
     *
     * @param args Command-line arguments passed to the application.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    /**
     * Configures and provides a bean for ModelMapper, a mapping library for Java objects.
     *
     * @return An instance of ModelMapper for object mapping.
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
