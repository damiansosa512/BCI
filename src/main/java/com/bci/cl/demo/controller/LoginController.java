package com.bci.cl.demo.controller;

import com.bci.cl.demo.dto.AuthUser;
import com.bci.cl.demo.dto.UserDto;
import com.bci.cl.demo.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    AuthService authService;

    @PostMapping("/api-auth/v1/")
    public Map<String, Object> login(@Valid @RequestBody AuthUser authUser) {
        return authService.generateToken(authUser.getUser(), authUser.getPass());
    }

}
