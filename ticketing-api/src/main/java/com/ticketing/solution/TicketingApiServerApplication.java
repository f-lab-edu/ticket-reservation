package com.ticketing.solution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TicketingApiServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TicketingApiServerApplication.class, args);
    }
}
