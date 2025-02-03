package com.smartjob.users.service.impl;

import com.smartjob.users.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
        updateUserLoginDate(user);
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.emptyList()
        );
    }

    private void updateUserLoginDate(com.smartjob.users.model.User user){
        user.setLastSessionDate(new Date());
        userRepository.save(user);
    }
}
