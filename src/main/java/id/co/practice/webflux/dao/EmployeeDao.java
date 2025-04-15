package id.co.practice.webflux.dao;

import id.co.practice.webflux.dto.request.EmployeeDto;
import id.co.practice.webflux.mapper.EmployeeMapper;
import id.co.practice.webflux.repository.EmployeeRepository;
import jakarta.validation.constraints.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmployeeDao implements Dao<EmployeeDto, UUID> {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public Mono<EmployeeDto> save(EmployeeDto data) {
        return Mono.just(this.employeeMapper.mapDtoToEntity(data))
                .flatMap(this.employeeRepository::save)
                .map(this.employeeMapper::mapEntityToDto);
    }

    @Override
    public Mono<EmployeeDto> findById(UUID id) {
        return this.employeeRepository.findById(id)
                .map(this.employeeMapper::mapEntityToDto);
    }

    @Override
    public Flux<EmployeeDto> findAllPaged(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int size = pageable.getPageSize();
        return this.employeeRepository.findAllBy(size, offset)
                .map(this.employeeMapper::mapEntityToDto);
    }

    @Override
    public Mono<EmployeeDto> update(UUID id, EmployeeDto data) {
        return this.employeeRepository.findById(id)
                .map(employeeEntity -> {
                    employeeEntity.setIdentityId(data.getIdentityId());
                    employeeEntity.setName(data.getName());
                    employeeEntity.setBirthDate(data.getBirthDate());
                    employeeEntity.setJoinDate(data.getJoinDate());
                    employeeEntity.setSalary(data.getSalary());
                    employeeEntity.setStatus(data.getStatus());
                    return employeeEntity;
                })
                .flatMap(this.employeeRepository::save)
                .map(this.employeeMapper::mapEntityToDto);
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return this.employeeRepository.deleteById(id);
    }
}
