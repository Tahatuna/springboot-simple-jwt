package com.javalier.simplespringbootjwt.controller;

import com.javalier.simplespringbootjwt.Reqs.LoginReq;
import com.javalier.simplespringbootjwt.security.JWTManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthAPIs {

    private JWTManager jwtManager;

    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthAPIs(JWTManager jwtManager, AuthenticationManager authenticationManager) {
        this.jwtManager = jwtManager;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping(value = "login")
    public ResponseEntity<String> login(@RequestBody LoginReq loginReq) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginReq.getUsername(), loginReq.getPassword()));
            return ResponseEntity.ok(jwtManager.genarateJwt(loginReq.getUsername()));
        } catch (Exception e) {
            throw e;
        }
    }

}
