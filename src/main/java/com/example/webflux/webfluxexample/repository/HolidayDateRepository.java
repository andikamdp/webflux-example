package com.example.webflux.webfluxexample.repository;

import com.example.webflux.webfluxexample.model.HolidayDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HolidayDateRepository extends JpaRepository<HolidayDate, UUID> {
    Optional<HolidayDate> findFirstByDateOrderByCreatedDateAsc(ZonedDateTime holidayDate);
}
