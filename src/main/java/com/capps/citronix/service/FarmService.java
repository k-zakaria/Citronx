package com.capps.citronix.service;

import com.capps.citronix.domain.Farm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FarmService {
    Page<Farm> findAll(Pageable pageable);

    Farm findById(UUID id);

    Farm save(Farm farm);

    Farm update(Farm farm, UUID id);

    boolean delete(UUID id);
}
