package com.dlms.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.dlms.backend")
// JPA (MySQL) Repositories
@EnableJpaRepositories(basePackages = {
        "com.dlms.backend.auth.repository",
        "com.dlms.backend.enrollment.repository"
})
@EntityScan(basePackages = {
        "com.dlms.backend.auth.entity",
        "com.dlms.backend.enrollment.entity" // Adjust if Enrollment entities are in different package
})
// MongoDB Repositories
@EnableMongoRepositories(basePackages = {
        "com.dlms.backend.course.repository",
        "com.dlms.backend.media.repository"
})
public class DLMSApplication {

    public static void main(String[] args) {
        SpringApplication.run(DLMSApplication.class, args);
    }
}
