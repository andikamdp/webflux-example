package com.example.webflux.webfluxexample.repository;

import com.example.webflux.webfluxexample.model.RateInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RateInfoRepository extends JpaRepository<RateInfo, UUID> {
    Optional<RateInfo> findFirstByCurrencyOrderByRegisteredDateAsc(String currency);
}
