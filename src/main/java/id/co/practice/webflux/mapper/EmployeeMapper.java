package id.co.practice.webflux.mapper;

import id.co.practice.webflux.dto.request.EmployeeDto;
import id.co.practice.webflux.entity.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeEntity mapDtoToEntity(EmployeeDto data);

    EmployeeDto mapEntityToDto(EmployeeEntity data);
}
