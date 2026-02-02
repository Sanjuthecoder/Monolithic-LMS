package com.dlms.backend.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.dlms.backend.auth.entity.Users;
import com.dlms.backend.auth.repository.UsersRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usersRepository.count() == 0) {
            Users admin = new Users();
            admin.setUserName("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setEmail("admin@dlms.com");
            admin.setRole("ADMIN");
            usersRepository.save(admin);

            Users instructor = new Users();
            instructor.setUserName("instructor");
            instructor.setPassword(passwordEncoder.encode("instructor123"));
            instructor.setEmail("instructor@dlms.com");
            instructor.setRole("INSTRUCTOR");
            usersRepository.save(instructor);

            Users student = new Users();
            student.setUserName("student");
            student.setPassword(passwordEncoder.encode("student123"));
            student.setEmail("student@dlms.com");
            student.setRole("STUDENT");
            usersRepository.save(student);

            System.out.println("Dummy data seeded: admin, instructor, student users created.");
        }
    }
}
