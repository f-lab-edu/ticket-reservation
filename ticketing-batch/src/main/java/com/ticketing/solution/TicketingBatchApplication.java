package com.ticketing.solution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TicketingBatchApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketingBatchApplication.class, args);
    }

}