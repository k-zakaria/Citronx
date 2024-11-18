package com.capps.citronix.service;

import com.capps.citronix.domain.Harvest;
import com.capps.citronix.service.dto.harvest.HarvestDTO;

import java.util.List;
import java.util.UUID;

public interface HarvestService {
    List<Harvest> findAll();

    Harvest findById(UUID id);

    Harvest save(HarvestDTO harvestDTO);

    Harvest update(UUID id, HarvestDTO harvestDTO);

    void delete(UUID id);
}
