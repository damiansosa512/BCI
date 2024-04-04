package com.bci.cl.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Objects;

@Data
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class PhoneDto {

    private String number;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneDto phoneDto = (PhoneDto) o;
        return Objects.equals(number, phoneDto.number) && Objects.equals(citycode, phoneDto.citycode) && Objects.equals(countrycode, phoneDto.countrycode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, citycode, countrycode);
    }

    private String citycode;

    private String countrycode;


}
