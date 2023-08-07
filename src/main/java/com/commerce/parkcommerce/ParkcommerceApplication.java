package com.commerce.parkcommerce;

import jakarta.persistence.EntityListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ParkcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkcommerceApplication.class, args);
    }
}
