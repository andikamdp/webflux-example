package id.co.practice.webflux.dao;

import id.co.practice.webflux.dto.request.HolidayDto;
import id.co.practice.webflux.mapper.HolidayMapper;
import id.co.practice.webflux.repository.HolidayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class HolidayDao implements Dao<HolidayDto, UUID> {

    private final HolidayRepository holidayRepository;
    private final HolidayMapper holidayMapper;

    @Override
    public Mono<HolidayDto> save(HolidayDto data) {
        return Mono.just(this.holidayMapper.mapDtoToEntity(data))
                .flatMap(this.holidayRepository::save)
                .map(this.holidayMapper::mapEntityToDto);
    }

    @Override
    public Mono<HolidayDto> findById(UUID id) {
        return this.holidayRepository.findById(id)
                .map(this.holidayMapper::mapEntityToDto);
    }

    @Override
    public Flux<HolidayDto> findAllPaged(Pageable pageable) {
        int offset = pageable.getPageNumber() * pageable.getPageSize();
        int size = pageable.getPageSize();
        return this.holidayRepository.findAllBy(size, offset)
                .map(this.holidayMapper::mapEntityToDto);
    }

    @Override
    public Mono<HolidayDto> update(UUID id, HolidayDto data) {
        return this.holidayRepository.findById(id)
                .map(holidayEntity -> {
                    holidayEntity.setHolidayDate(data.getHolidayDate());
                    holidayEntity.setDescription(data.getDescription());
                    holidayEntity.setStatus(data.getStatus());
                    return holidayEntity;
                })
                .flatMap(this.holidayRepository::save)
                .map(this.holidayMapper::mapEntityToDto);
    }

    @Override
    public Mono<Void> delete(UUID id) {
        return this.holidayRepository.deleteById(id);
    }
}
