package com.smartjob.users.service.impl;

import com.smartjob.users.model.User;
import com.smartjob.users.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class DataInitializer {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initData() {
        if (userRepository.count() == 0) {
            userRepository.save(new User(
                    UUID.randomUUID(),
                    "user1",
                    "abc1@abc.com",
                    "Password1",
                    new Date(),
                    new Date(),
                    "token-init"));
            userRepository.save(new User(
                    UUID.randomUUID(),
                    "user2",
                    "abc2@abc.com",
                    "Password2",
                    new Date(),
                    new Date(),
                    "token-init"));
        }
    }
}
