package id.co.practice.webflux.repository;

import id.co.practice.webflux.entity.HolidayEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface HolidayRepository extends R2dbcRepository<HolidayEntity, UUID> {
    @Query("SELECT * FROM public.tbl_holiday_tm limit :size offset :offset")
    Flux<HolidayEntity> findAllBy(int size, int offset);
}
