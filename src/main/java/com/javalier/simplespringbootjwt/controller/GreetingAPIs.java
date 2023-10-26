package com.javalier.simplespringbootjwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET})
public class GreetingAPIs {

    @GetMapping("/sayHelloToMe")
    public ResponseEntity<String> sayHelloToMe() {
        return ResponseEntity.ok("Hello");
    }

}
