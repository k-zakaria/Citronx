package com.capps.citronix.service;

import com.capps.citronix.domain.HarvestDetails;
import com.capps.citronix.service.dto.harvestdetails.HarvestDetailsDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface HarvestDetailsService {
    Page<HarvestDetails> findAll(Pageable pageable);

    HarvestDetails findById(UUID id);

    HarvestDetails save(HarvestDetailsDTO harvestDetailsDTO);

    HarvestDetails update(HarvestDetailsDTO harvestDetailsDTO, UUID id);

    boolean delete(UUID id);
}
