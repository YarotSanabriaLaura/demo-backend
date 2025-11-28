package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Home {

    @GetMapping("/")
    public Map<String, Object> hello() {
        return Map.of(
                "message", "Bienvenido a SIS 414 G2 v2",
                "status", "OK"
        );
    }

    @GetMapping("/{param}")
    public Map<String, String> helloParam(@PathVariable String param) {
        return Map.of("saludo", "Bienvenido a SIS 414 G2 v2 " + param);
    }
}
