package com.javalier.simplespringbootjwt.security;

import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private Map<String, String> users = new HashMap<>();

    @PostConstruct
    public void init() {
        users.put("username", passwordEncoder.encode("1234"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (users.containsKey(username)) {
            return new User(username, users.get(username), new ArrayList<>());
        }
        throw new UsernameNotFoundException(username);
    }

}