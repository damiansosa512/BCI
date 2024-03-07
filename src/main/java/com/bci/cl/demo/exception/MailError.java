package com.bci.cl.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class MailError extends RuntimeException{

    public Integer code;

    public String message;

    public MailError(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
