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
    public Harvest save(HarvestDTO harvestDTO) {
        Field field = fieldRepository.findById(harvestDTO.getFieldId())
                .orElseThrow(() -> new HarvestNotFoundException("Champ introuvable !"));
        boolean exists = harvestRepository.existsByFieldAndSaison(field, harvestDTO.getSaison());
        if (exists) {
            throw new HarvestAlreadyExistsException("Une récolte pour ce champ existe déjà pour la saison " + harvestDTO.getSaison());
        }

        Harvest harvest = Harvest.builder()
                .date(harvestDTO.getDate())
                .totalQuantity(harvestDTO.getTotalQuantity())
                .saison(harvestDTO.getSaison())
                .field(field)
                .build();
        return harvestRepository.save(harvest);
    }

    @Override
    public Harvest update(UUID id, HarvestDTO harvestDTO) {
        Harvest existingHarvest = harvestRepository.findById(id)
                .orElseThrow(() -> new HarvestNotFoundException("Récolte introuvable !"));
        Field field = fieldRepository.findById(harvestDTO.getFieldId())
                .orElseThrow(() -> new HarvestNotFoundException("Champ introuvable !"));

        existingHarvest.setDate(harvestDTO.getDate());
        existingHarvest.setTotalQuantity(harvestDTO.getTotalQuantity());
        existingHarvest.setSaison(harvestDTO.getSaison());
        existingHarvest.setField(field);

        return harvestRepository.save(existingHarvest);
    }

    @Override
    public void delete(UUID id) {
        if (!harvestRepository.existsById(id)) {
            throw new HarvestNotFoundException("Récolte introuvable !");
        }
        harvestRepository.deleteById(id);
    }
}
