package com.capps.citronix.service.impl;

import com.capps.citronix.domain.Farm;
import com.capps.citronix.domain.Field;
import com.capps.citronix.repository.FarmRepository;
import com.capps.citronix.repository.FieldRepository;
import com.capps.citronix.service.FieldService;
import com.capps.citronix.web.errors.farm.FarmNotFoundException;
import com.capps.citronix.web.errors.field.AreaConsistencyException;
import com.capps.citronix.web.errors.field.FieldNotFoundException;
import com.capps.citronix.web.errors.field.MaxFieldAreaExceededException;
import com.capps.citronix.web.errors.field.MaxFieldsExceededException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FieldServiceImpl implements FieldService {
    private final FieldRepository fieldRepository;
    private final FarmRepository farmRepository;

    @Override
    public Page<Field> findAll(Pageable pageable) {
        return fieldRepository.findAll(pageable);
    }

    @Override
    public Field findById(UUID id) {
        return fieldRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("Field not found!"));
    }

    @Override
    public Field save(Field field) {
        Farm farm = farmRepository.findById(field.getFarm().getId())
                .orElseThrow(() -> new FarmNotFoundException("Farm not found!"));

        if (field.getArea() < 1000) {
            throw new AreaConsistencyException("La superficie du champ doit être d'au moins 1 000 m² (0.1 hectare).");
        }

        float totalFieldArea = fieldRepository.sumAreaByFarm(farm.getId());

        if (totalFieldArea + field.getArea() >= farm.getArea()) {
            throw new AreaConsistencyException("La somme des superficies des champs (" +
                    (totalFieldArea + field.getArea()) + " m²) dépasse ou est égale à la superficie de la ferme (" +
                    farm.getArea() + " m²).");
        }

        float maxFieldArea = farm.getArea() * 0.5f;

        if (field.getArea() > maxFieldArea) {
            throw new MaxFieldAreaExceededException("La superficie du champ ne peut pas dépasser 50% de la superficie totale de la ferme.");
        }

        long fieldCount = fieldRepository.countByFarm(farm);

        if (fieldCount >= 10) {
            throw new MaxFieldsExceededException("La ferme a déjà atteint le nombre maximal de 10 champs.");
        }

        field.setFarm(farm);

        return fieldRepository.save(field);
    }

    @Override
    public Field update(Field field, UUID id) {
        Field existingField = fieldRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("Field not found!"));
        Farm farm = farmRepository.findById(field.getFarm().getId())
                .orElseThrow(() -> new FarmNotFoundException("Farm not found!"));

        if (field.getArea() < 1000) {
            throw new AreaConsistencyException("La superficie du champ doit être d'au moins 1 000 m² (0.1 hectare).");
        }

        float maxFieldArea = farm.getArea() * 0.5f;
        if (field.getArea() > maxFieldArea) {
            throw new MaxFieldAreaExceededException("La superficie du champ ne peut pas dépasser 50% de la superficie totale de la ferme.");
        }

        long fieldCount = fieldRepository.countByFarm(farm);
        if (fieldCount >= 10) {
            throw new MaxFieldsExceededException("La ferme a déjà atteint le nombre maximal de 10 champs.");
        }

        existingField.setArea(field.getArea());
        existingField.setFarm(farm);

        return fieldRepository.save(existingField);
    }

    @Override
    public boolean delete(UUID id) {
        Field field = fieldRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("Field not found!"));
        fieldRepository.delete(field);
        return true;
    }
}