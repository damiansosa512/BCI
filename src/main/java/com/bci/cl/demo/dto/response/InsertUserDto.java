package com.bci.cl.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class InsertUserDto {

    private UUID id;

    private LocalDateTime created_at;

    private LocalDateTime modified_at;

    private LocalDateTime last_login;

    private String token;

    private Boolean is_active;
}
