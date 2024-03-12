package com.bci.cl.demo.dto;

import com.bci.cl.demo.util.Patron;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID id;

    private String name;

    @Pattern(regexp = Patron.PATTERN, message = "El formato del campo password no es valido")
    private String password;

    @Email(message = "El campo no contiene formato de email")
    private String email;

    private LocalDateTime created_at;

    private LocalDateTime modified_at;

    private LocalDateTime last_login;

    private String token;

    private Boolean is_active;

    private List<PhoneDto> phones;

    private RolDto rol;
}
