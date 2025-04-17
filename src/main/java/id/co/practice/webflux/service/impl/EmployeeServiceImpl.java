package id.co.practice.webflux.service.impl;

import id.co.practice.webflux.dao.EmployeeDao;
import id.co.practice.webflux.dto.request.EmployeeDto;
import id.co.practice.webflux.exception.ExceptionType;
import id.co.practice.webflux.exception.GeneralErrorException;
import id.co.practice.webflux.exception.ValidationErrorException;
import id.co.practice.webflux.service.DataManagementService;
import id.co.practice.webflux.util.ReactiveLocaleUtil;
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
    private final ReactiveLocaleUtil reactiveLocaleUtil;

    @Override
    public Mono<EmployeeDto> save(EmployeeDto data) {
        return reactiveLocaleUtil.getLocale()
                .map(locale -> data.validate(false, locale))
                .flatMap(validationErrorList-> {
                            if (validationErrorList.isEmpty()) {
                                return employeeDao.save(data);
                            }
                            log.error("Employee Request Dto format not valid: {}", validationErrorList);
                            return Mono.error(new ValidationErrorException("Employee Request Dto format not valid", validationErrorList));
                });
    }

    @Override
    public Mono<EmployeeDto> findById(UUID id) {
        return employeeDao.findById(id)
                .switchIfEmpty(Mono.defer(() -> {
                    log.error("Employee with id {} not found", id);

                    return Mono.error(new GeneralErrorException("Employee with id {} not found", ExceptionType.NO_DATA_FOUND));
                }));
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
