package com.nononsensecode.secure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1.0/hello")
public class CommonController {
    @GetMapping
    public ResponseEntity<String> getHello() {
        return new ResponseEntity<String>("Hello World", HttpStatus.OK);
    }
}
