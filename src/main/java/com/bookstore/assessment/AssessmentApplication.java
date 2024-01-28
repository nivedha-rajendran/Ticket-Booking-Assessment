package com.bookstore.assessment;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * The main class for the Bookstore Assessment Application.
 * This class serves as the entry point for the Spring Boot application.
 * It is annotated with {@code @SpringBootApplication} to enable auto-configuration
 * and component scanning, and it defines a {@code @Bean} method to provide a
 * singleton instance of {@link org.modelmapper.ModelMapper}.
 *
 * <p>
 * The {@code main} method starts the Spring Boot application.
 * </p>
 *
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 * @see org.springframework.context.annotation.Bean
 * @see org.modelmapper.ModelMapper
 */

@SpringBootApplication
public class AssessmentApplication {


	/**
	 * The main method to start the Spring Boot application.
	 *
	 * @param args Command line arguments passed to the application.
	 * @see SpringApplication#run(Class, String...)
	 */
	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}

	/**
	 * Provides a singleton instance of {@link org.modelmapper.ModelMapper}.
	 * The {@code ModelMapper} is used for mapping objects between different classes.
	 *
	 * @return A singleton instance of {@code ModelMapper}.
	 * @see org.modelmapper.ModelMapper
	 */
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
