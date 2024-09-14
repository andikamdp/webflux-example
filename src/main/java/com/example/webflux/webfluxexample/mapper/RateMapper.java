package com.example.webflux.webfluxexample.mapper;

import com.example.webflux.webfluxexample.dto.RateInfoDto;
import com.example.webflux.webfluxexample.model.RateInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RateMapper {
    RateInfo mapDtoToEntity(RateInfoDto data);

    RateInfoDto mapEntityToDto(RateInfo data);
}
