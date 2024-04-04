package com.bci.cl.demo.services;

import com.bci.cl.demo.dto.UserDto;
import com.bci.cl.demo.dto.response.InsertUserDto;
import com.bci.cl.demo.dto.response.UpdateUserDto;
import com.bci.cl.demo.entity.PhoneEntity;
import com.bci.cl.demo.entity.UserEntity;
import com.bci.cl.demo.exception.MailError;
import com.bci.cl.demo.exception.UserNotFoundError;
import com.bci.cl.demo.mapper.PhoneMapper;
import com.bci.cl.demo.mapper.UserMapper;
import com.bci.cl.demo.repository.PhonesRepository;
import com.bci.cl.demo.repository.UserRepository;
import com.bci.cl.demo.util.Util;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final PhonesRepository phonesRepository;

    private final PhoneMapper phoneMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PhonesRepository phonesRepository, PhoneMapper phoneMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.phonesRepository = phonesRepository;
        this.phoneMapper = phoneMapper;
    }


    public InsertUserDto insertTransaction(UserDto userDto, String authorization) {
        userDto.setToken(Util.getToken(authorization));
        Optional<UserEntity> userfinded = findUser(userDto.getEmail());
        if (!userfinded.isPresent()) {
            Optional<UserEntity> userEntity = processUser(userDto);
            return userMapper.responseInsert(userEntity.get());
        }
        throw new MailError(0, "El usuario ya se encuentra registrado");
    }

    public UserDto getTransaction(UUID uuid) {
        Optional<UserEntity> userEntity = userRepository.findById(uuid);
        return userEntity.map(userMapper::toDto).orElse(null);
    }

    @Transactional
    public String deleteTransaction(UUID uuid) {
        Long result = userRepository.deleteById(uuid);
        if (result == 0L) {
            throw new UserNotFoundError(1, "No se encuentra el usuario que desea eliminar");
        }
        return "Borrado exitoso";
    }

    public UpdateUserDto updateTransaction(UserDto userDto, String authorization) {
        userDto.setToken(Util.getToken(authorization));
        Optional<UserEntity> userfinded = findUser(userDto.getEmail());
        if (userfinded.isPresent()) {
            userDto.setId(userfinded.get().getId());
            Optional<UserEntity> userEntity = processUser(userDto);
            return userMapper.responseUpdate(userEntity.get());
        }
        throw new MailError(0, "El usuario no se encuentra registrado");
    }


    private Optional<UserEntity> processUser(UserDto userDto) {
        UserEntity user = userMapper.toEntity(userDto);
        userDto.getPhones().stream().forEach(
                t -> {
                    PhoneEntity phoneEntity = phoneMapper.toEntity(t);
                    phoneEntity.setUser(user);
                    user.getPhones().add(phoneEntity);
                }
        );
        return storeUserAndPhones(user);

    }

    private void processPhones(UserDto userDto, UUID id) {
        List<PhoneEntity> phoneEntityList = new ArrayList<>();
        userDto.getPhones().stream().forEach(t -> {
            PhoneEntity phoneEntity = phoneMapper.toEntity(t);
            phoneEntityList.add(phoneEntity);
        });
        phonesRepository.saveAll(phoneEntityList);
    }

    private Optional<UserEntity> findUser(String email) {
        return userRepository.findByEmail(email);
    }

    private Optional<UserEntity> storeUserAndPhones(UserEntity user) {
        UserEntity userSaved = userRepository.save(user);
        return Optional.of(userSaved);
    }

    public Optional<UserEntity> findByNameAndPassword(String userName, String password) {
        return userRepository.findByNameAndPassword(userName, password);
    }
}
