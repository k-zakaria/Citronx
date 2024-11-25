package com.capps.citronix.service;

import com.capps.citronix.domain.Farm;
import com.capps.citronix.service.dto.FarmSearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface FarmService {
    Page<Farm> findAll(Pageable pageable);

    Farm findById(UUID id);

    Farm save(Farm farm);

    Farm update(Farm farm, UUID id);

    List<Farm> searchFarms(FarmSearchCriteria criteria);

    boolean delete(UUID id);
}
