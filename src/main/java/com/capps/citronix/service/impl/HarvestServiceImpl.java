package com.capps.citronix.service.impl;

import com.capps.citronix.domain.Field;
import com.capps.citronix.domain.Harvest;
import com.capps.citronix.repository.FieldRepository;
import com.capps.citronix.repository.HarvestRepository;
import com.capps.citronix.service.HarvestService;
import com.capps.citronix.service.dto.harvest.HarvestDTO;
import com.capps.citronix.web.errors.harvest.HarvestAlreadyExistsException;
import com.capps.citronix.web.errors.harvest.HarvestNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HarvestServiceImpl implements HarvestService {

    private final HarvestRepository harvestRepository;
    private final FieldRepository fieldRepository;

    @Override
    public List<Harvest> findAll() {
        return harvestRepository.findAll();
    }

    @Override
    public Harvest findById(UUID id) {
        return harvestRepository.findById(id)
                .orElseThrow(() -> new HarvestNotFoundException("Récolte introuvable !"));
    }

    @Override
    public Harvest save(Harvest harvest) {
        Field field = fieldRepository.findById(harvest.getField().getId())
                .orElseThrow(() -> new HarvestNotFoundException("Champ introuvable !"));

        boolean exists = harvestRepository.existsByFieldAndSaison(field, harvest.getSaison());
        if (exists) {
            throw new HarvestAlreadyExistsException("Une récolte pour ce champ existe déjà pour la saison " + harvest.getSaison());
        }

        harvest.setField(field);
        return harvestRepository.save(harvest);
    }

    @Override
    public Harvest update(UUID id, Harvest harvest) {
        Harvest existingHarvest = harvestRepository.findById(id)
                .orElseThrow(() -> new HarvestNotFoundException("Récolte introuvable !"));
        Field field = fieldRepository.findById(harvest.getField().getId())
                .orElseThrow(() -> new HarvestNotFoundException("Champ introuvable !"));

        existingHarvest.setDate(harvest.getDate());
        existingHarvest.setTotalQuantity(harvest.getTotalQuantity());
        existingHarvest.setSaison(harvest.getSaison());
        existingHarvest.setField(field);

        return harvestRepository.save(existingHarvest);
    }

    @Override
    public void delete(UUID id) {
        Harvest existing = harvestRepository.findById(id)
                        .orElseThrow(() -> new HarvestNotFoundException("Haverst not found!"));
        harvestRepository.delete(existing);
    }
}
