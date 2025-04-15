package id.co.practice.webflux.service.impl;

import id.co.practice.webflux.dao.EmployeeDao;
import id.co.practice.webflux.dto.request.EmployeeDto;
import id.co.practice.webflux.repository.EmployeeRepository;
import id.co.practice.webflux.service.DataManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeServiceImpl implements DataManagementService<EmployeeDto, UUID> {

    private final EmployeeDao employeeDao;

    @Override
    public Mono<EmployeeDto> save(EmployeeDto data) {
        return employeeDao.save(data);
    }

    @Override
    public Mono<EmployeeDto> findById(UUID id) {
        return employeeDao.findById(id);
    }

    @Override
    public Flux<EmployeeDto> findAllPaged(Pageable pageable) {
        return employeeDao.findAllPaged(pageable);
    }

    @Override
    public Mono<EmployeeDto> update(UUID id, EmployeeDto data) {
        return employeeDao.update(id, data);
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return employeeDao.delete(id);
    }
}
