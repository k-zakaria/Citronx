package com.capps.citronix.service.impl;

import com.capps.citronix.domain.Farm;
import com.capps.citronix.domain.Field;
import com.capps.citronix.repository.FarmRepository;
import com.capps.citronix.repository.FieldRepository;
import com.capps.citronix.service.FieldService;
import com.capps.citronix.service.dto.field.FieldDTO;
import com.capps.citronix.web.errors.farm.FarmNotFoundException;
import com.capps.citronix.web.errors.field.FieldNotFoundException;
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
        Field field = fieldMapper.toFieldEntity(fieldDTO);
        field.setFarm(farm);
        return fieldRepository.save(field);
    }

    @Override
    public Field update(FieldDTO fieldDTO, UUID id) {
        Field existingField = fieldRepository.findById(id)
                .orElseThrow(() -> new FieldNotFoundException("Field not found!"));
        Farm farm = farmRepository.findById(fieldDTO.getFarmId())
                .orElseThrow(() -> new FarmNotFoundException("Farm not found!"));

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
