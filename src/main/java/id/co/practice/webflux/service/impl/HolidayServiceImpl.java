package id.co.practice.webflux.service.impl;

import id.co.practice.webflux.dao.HolidayDao;
import id.co.practice.webflux.dto.request.HolidayDto;
import id.co.practice.webflux.service.DataManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements DataManagementService<HolidayDto, UUID> {
    private final HolidayDao holidayDao;

    @Override
    public Mono<HolidayDto> save(HolidayDto data) {
        return this.holidayDao.save(data)
                .doOnSuccess(holidayDto -> log.info("Holiday saved: {}", holidayDto))
                .doOnError(error -> log.error("Error saving holiday: {}", error.getMessage()));
    }

    @Override
    public Mono<HolidayDto> findById(UUID id) {
        return this.holidayDao.findById(id)
                .doOnSuccess(holidayDto -> log.info("Holiday found: {}", holidayDto))
                .doOnError(error -> log.error("Error finding holiday: {}", error.getMessage()));
    }

    @Override
    public Flux<HolidayDto> findAllPaged(Pageable pageable) {
        return this.holidayDao.findAllPaged(pageable)
                .doOnNext(holidayDto -> log.info("Holiday found: {}", holidayDto))
                .doOnError(error -> log.error("Error finding holidays: {}", error.getMessage()));
    }

    @Override
    public Mono<HolidayDto> update(UUID id, HolidayDto data) {
        return this.holidayDao.update(id, data)
                .doOnSuccess(holidayDto -> log.info("Holiday updated: {}", holidayDto))
                .doOnError(error -> log.error("Error updating holiday: {}", error.getMessage()));
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return this.holidayDao.delete(id)
                .doOnSuccess(aVoid -> log.info("Holiday deleted: {}", id))
                .doOnError(error -> log.error("Error deleting holiday: {}", error.getMessage()));
    }
}
