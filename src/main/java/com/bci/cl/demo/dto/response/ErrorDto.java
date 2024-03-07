package com.bci.cl.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDto {

    private Integer code;

    private String message;
}
