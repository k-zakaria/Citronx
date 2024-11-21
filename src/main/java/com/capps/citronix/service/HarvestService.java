package com.capps.citronix.service;

import com.capps.citronix.domain.Harvest;
import com.capps.citronix.service.dto.harvest.HarvestDTO;

import java.util.List;
import java.util.UUID;

public interface HarvestService {
    List<Harvest> findAll();

    Harvest findById(UUID id);

    Harvest save(Harvest harvest);

    Harvest update(UUID id, Harvest harvest);

    void delete(UUID id);
}
