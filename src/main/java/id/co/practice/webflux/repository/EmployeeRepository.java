package id.co.practice.webflux.repository;

import id.co.practice.webflux.entity.EmployeeEntity;
import id.co.practice.webflux.entity.HolidayEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends R2dbcRepository<EmployeeEntity, UUID> {
    @Query("SELECT * FROM public.tbl_employee_tm limit :size offset :offset")
    Flux<EmployeeEntity> findAllBy(int size, int offset);
}
