package com.nononsensecode.secure.controller;

import com.nononsensecode.secure.config.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/greetings")
public class SecureController {

    private UserValidator userValidator;

    @Autowired
    public SecureController(UserValidator validator) {
        this.userValidator = validator;
    }

    @GetMapping("/name/{name:[a-z]+}")
    public ResponseEntity<String> greetByName(@PathVariable("name") String name) {
        return new ResponseEntity<String>("Hello " + name, HttpStatus.OK);
    }

    @GetMapping("/user/{userId:\\d+}")
    public ResponseEntity<String> greetUsers(@PathVariable("userId") Integer userId) {
        return new ResponseEntity<String>("Hello " + userValidator.userName(userId), HttpStatus.OK);
    }
}
