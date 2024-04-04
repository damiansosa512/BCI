package com.bci.cl.demo.mapper;

import com.bci.cl.demo.dto.UserDto;
import com.bci.cl.demo.dto.response.InsertUserDto;
import com.bci.cl.demo.dto.response.UpdateUserDto;
import com.bci.cl.demo.entity.PhoneEntity;
import com.bci.cl.demo.entity.UserEntity;
import org.mapstruct.*;

import java.util.ArrayList;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    @Mappings({
            @Mapping(target = "name", source = "userDto.name"),
            @Mapping(target = "password", source = "userDto.password"),
            @Mapping(target = "email", source = "userDto.email"),
            @Mapping(target = "token", source = "userDto.token"),
            @Mapping(target = "rol", source = "userDto.rol"),
            @Mapping(target = "phones", source = "email", qualifiedByName = "buildVector")
    })
    UserEntity toEntity(UserDto userDto);

    @Mappings({
            @Mapping(target = "rol", source = "userEntity.rol"),
            @Mapping(target = "created_at", source = "userEntity.createdAt"),
            @Mapping(target = "modified_at", source = "userEntity.modifiedAt"),
            @Mapping(target = "last_login", source = "userEntity.lastLogin")

    })
    UserDto toDto(UserEntity userEntity);


    @Mappings({
            @Mapping(target = "id", source = "userEntity.id"),
            @Mapping(target = "created_at", source = "userEntity.createdAt"),
            @Mapping(target = "modified_at", source = "userEntity.modifiedAt"),
            @Mapping(target = "last_login", source = "userEntity.rol.lastLogin"),
            @Mapping(target = "is_active", source = "userEntity.rol.isActive"),
            @Mapping(target = "token", source = "userEntity.token"),
    })
    InsertUserDto responseInsert(UserEntity userEntity);

    @Mappings({
            @Mapping(target = "id", source = "userEntity.id"),
            @Mapping(target = "modified_at", source = "userEntity.modifiedAt"),
            @Mapping(target = "token", source = "userEntity.token")
    })
    UpdateUserDto responseUpdate(UserEntity userEntity);

    @Named("buildVector")
    default ArrayList<PhoneEntity> buildVector(String email) {
        return new ArrayList<PhoneEntity>();
    }

}
