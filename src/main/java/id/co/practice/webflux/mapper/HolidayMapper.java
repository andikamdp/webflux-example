package id.co.practice.webflux.mapper;

import id.co.practice.webflux.dto.request.HolidayDto;
import id.co.practice.webflux.entity.HolidayEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HolidayMapper {
    HolidayEntity mapDtoToEntity(HolidayDto data);

    HolidayDto mapEntityToDto(HolidayEntity data);
}
