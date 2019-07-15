package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.dao.CSVToDatabase;

/**
 * On start, it invoke CSVToDatabase's `initDatabase()` to populate the Cassandra database
 * @author aahmed
 *
 */
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
    @Bean
    CommandLineRunner initDatabase(CSVToDatabase repository) {
        return args -> {
        	repository.initDatabase();
        };
    }

}
