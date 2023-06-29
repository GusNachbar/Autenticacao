package com.learning.autentica.controller;

import com.learning.autentica.dto.HelloDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloAuthWordController {

    @GetMapping
    private ResponseEntity<HelloDTO> sayHello() {
        return ResponseEntity.ok(new HelloDTO("Hello, you are authenticated"));
    }
}
