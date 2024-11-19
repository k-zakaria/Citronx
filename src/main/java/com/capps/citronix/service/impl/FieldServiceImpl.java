package com.capps.citronix.service.impl;

import com.capps.citronix.domain.Farm;
import com.capps.citronix.domain.Field;
import com.capps.citronix.repository.FarmRepository;
import com.capps.citronix.repository.FieldRepository;
import com.capps.citronix.service.FieldService;
import com.capps.citronix.service.dto.field.FieldDTO;
import com.capps.citronix.web.errors.farm.FarmNotFoundException;
import com.capps.citronix.web.errors.field.FieldNotFoundException;
import com.capps.citronix.web.errors.field.MaxFieldAreaExceededException;
import com.capps.citronix.web.errors.field.MaxFieldsExceededException;
import com.capps.citronix.web.vm.mapper.FieldMapper;
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
    private final FieldMapper fieldMapper;

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
    public Field save(FieldDTO fieldDTO) {
        Farm farm = farmRepository.findById(fieldDTO.getFarmId())
                .orElseThrow(() -> new FarmNotFoundException("Farm not found!"));

        float maxFieldArea = farm.getArea() * 0.5f;
        if (fieldDTO.getArea() > maxFieldArea) {
            throw new MaxFieldAreaExceededException("La superficie du champ ne peut pas dépasser 50% de la superficie totale de la ferme.");
        }

        long fieldCount = fieldRepository.countByFarm(farm); // Vérification du nombre maximal de champs
        if (fieldCount >= 10) {
            throw new MaxFieldsExceededException("La ferme a déjà atteint le nombre maximal de 10 champs.");
        }

        Field field = fieldMapper.toFieldEntity(fieldDTO); // Mapper le DTO à l'entité
        field.setFarm(farm); // Associer la ferme

        return fieldRepository.save(field);
    }

    @Override
    public Field update(FieldDTO fieldDTO, UUID id) {
        Field existingField = fieldRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("Field not found!"));
        Farm farm = farmRepository.findById(fieldDTO.getFarmId())
                .orElseThrow(() -> new FarmNotFoundException("Farm not found!"));

        float maxFieldArea = farm.getArea() * 0.5f;
        if (fieldDTO.getArea() > maxFieldArea) {
            throw new MaxFieldAreaExceededException("La superficie du champ ne peut pas dépasser 50% de la superficie totale de la ferme.");
        }

        long fieldCount = fieldRepository.countByFarm(farm); // Vérification du nombre maximal de champs
        if (fieldCount >= 10) {
            throw new MaxFieldsExceededException("La ferme a déjà atteint le nombre maximal de 10 champs.");
        }

        existingField.setArea(fieldDTO.getArea());
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