package com.bci.cl.demo.services;

import com.bci.cl.demo.dto.PhoneDto;
import com.bci.cl.demo.dto.UserDto;
import com.bci.cl.demo.entity.UserEntity;
import com.bci.cl.demo.exception.MailError;
import com.bci.cl.demo.mapper.PhoneMapper;
import com.bci.cl.demo.mapper.UserMapper;
import com.bci.cl.demo.repository.PhonesRepository;
import com.bci.cl.demo.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;

    @Spy
    private UserMapper userMapper =
            Mappers.getMapper(UserMapper.class);

    @Mock
    private PhonesRepository phonesRepository;

    private PhoneMapper phoneMapper;

    Optional<UserEntity> userEntity;

    @BeforeEach
    void setup() {
        userEntity = Optional.of(UserEntity.builder().build());
        userService = new UserServiceImpl(userRepository, userMapper, phonesRepository, phoneMapper);
    }

    @Test
    void insertTransactionOk() {
        PhoneDto phoneDto = PhoneDto.builder().build();
        UserDto userDto = UserDto.builder().name("Diego").email("test@gmail.com").phones(Arrays.asList(phoneDto)).build();
        when(userRepository.findByEmail("test@gmail.com")).thenReturn(Optional.empty());
        when(userRepository.save(Mockito.any())).thenReturn(Optional.of(UserEntity.builder().build()).get());
        assertThatCode(() -> userService.insertTransaction(userDto, "ADMIN")).doesNotThrowAnyException();
    }

    @Test
    void insertTransactionWithError() {
        UserDto userDto = UserDto.builder().email("test@gmail.com").build();
        when(userRepository.findByEmail("test@gmail.com")).thenReturn(userEntity);
        assertThrows(MailError.class, () -> userService.insertTransaction(userDto, "xx"));
    }

}
