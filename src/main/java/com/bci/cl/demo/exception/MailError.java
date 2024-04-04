package com.bci.cl.demo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
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
