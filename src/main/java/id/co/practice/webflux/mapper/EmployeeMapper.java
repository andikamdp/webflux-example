package id.co.practice.webflux.mapper;

import id.co.practice.webflux.dto.request.EmployeeDto;
import id.co.practice.webflux.dto.request.HolidayDto;
import id.co.practice.webflux.entity.EmployeeEntity;
import id.co.practice.webflux.entity.HolidayEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeEntity mapDtoToEntity(EmployeeDto data);

    EmployeeDto mapEntityToDto(EmployeeEntity data);
}
