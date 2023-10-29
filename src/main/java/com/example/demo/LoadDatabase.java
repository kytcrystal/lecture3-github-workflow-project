package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(ExpenseRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Expense("Test 1", 50.0F, "02/01/2023")));
            log.info("Preloading " + repository.save(new Expense("Test 2", 30.0F, "03/01/2023")));
            log.info("Preloading " + repository.save(new Expense("Test 3", 60.0F, "03/01/2023")));
            log.info("Preloading " + repository.save(new Expense("Test 4", 30.0F, "04/01/2023")));

        };
    }
}