package com.capps.citronix.service;

import com.capps.citronix.domain.HarvestDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface HarvestDetailsService {
    Page<HarvestDetails> findAll(Pageable pageable);

    HarvestDetails findById(UUID id);

    HarvestDetails save(HarvestDetails harvestDetails);

    HarvestDetails update(HarvestDetails harvestDetails, UUID id);

    boolean delete(UUID id);
}
