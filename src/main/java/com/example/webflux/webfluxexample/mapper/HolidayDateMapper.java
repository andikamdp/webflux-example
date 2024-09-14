package com.example.webflux.webfluxexample.mapper;

import com.example.webflux.webfluxexample.dto.HolidayDateDto;
import com.example.webflux.webfluxexample.model.HolidayDate;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HolidayDateMapper {
    HolidayDate mapDtoToEntity(HolidayDateDto data);

    HolidayDateDto mapEntityToDto(HolidayDate data);
}
