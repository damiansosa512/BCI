package com.bci.cl.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
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
