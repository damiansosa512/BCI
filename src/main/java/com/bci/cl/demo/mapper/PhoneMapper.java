package com.bci.cl.demo.mapper;

import com.bci.cl.demo.dto.PhoneDto;
import com.bci.cl.demo.entity.PhoneEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PhoneMapper {

    PhoneEntity toEntity(PhoneDto phoneDto);

    PhoneDto toDto(PhoneEntity phoneEntity);
}
