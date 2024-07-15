package com.connie.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class CustomerServiceAssignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceAssignmentApplication.class, args);
    }

}
