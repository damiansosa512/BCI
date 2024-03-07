package com.bci.cl.demo.controller;

import com.bci.cl.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    AuthService authService;

    @PostMapping("/api-auth/v1/")
    public Map<String, Object> login(@RequestParam(value = "user", required = true) String username, @RequestParam(value = "pass", required = true) String pass) {
        return authService.generateToken(username, pass);
    }
}
