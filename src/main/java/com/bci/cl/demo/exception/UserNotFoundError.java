package com.bci.cl.demo.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserNotFoundError extends RuntimeException{
    public Integer code;

    public String message;

    public UserNotFoundError(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
