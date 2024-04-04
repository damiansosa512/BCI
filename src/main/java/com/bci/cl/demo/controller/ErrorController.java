package com.bci.cl.demo.controller;

import com.bci.cl.demo.dto.response.ErrorDto;
import com.bci.cl.demo.exception.MailError;
import com.bci.cl.demo.exception.UserNotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler({MailError.class, UserNotFoundError.class})
    public ResponseEntity<ErrorDto> manageEmailError(MailError e){
        return new ResponseEntity<>(ErrorDto.builder().code(e.getCode()).message(e.getMessage()).build(), HttpStatus.BAD_REQUEST);
    }

}
