package com.bci.cl.demo.controller;

import com.bci.cl.demo.dto.UserDto;
import com.bci.cl.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api-user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/v1/")
    public ResponseEntity<?> postUser(@Valid @RequestBody UserDto userDto, @RequestHeader("Authorization") String authorization, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return validation(bindingResult);
        }
        return new ResponseEntity<>(userService.insertTransaction(userDto, authorization), HttpStatus.CREATED);
    }

    @GetMapping("/v1/{uuid}")
    public ResponseEntity<UserDto> postUser(@PathVariable UUID uuid) {
        return new ResponseEntity<>(userService.getTransaction(uuid), HttpStatus.OK);
    }

    @DeleteMapping("/v1/{uuid}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID uuid) {
        return new ResponseEntity<>(userService.deleteTransaction(uuid), HttpStatus.OK);
    }

    @PutMapping("/v1/")
    public ResponseEntity<?> puUser(@Valid @RequestBody UserDto userDto, @RequestHeader("Authorization") String authorization, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()) {
            return validation(bindingResult);
        }
        return new ResponseEntity<>(userService.updateTransaction(userDto, authorization), HttpStatus.OK);
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), "El campo " + err.getField() + ", presenta los siguientes inconvenientes: " + err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }

}
