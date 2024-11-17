package com.capps.citronix.service;

import com.capps.citronix.domain.Field;
import com.capps.citronix.service.dto.field.FieldDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FieldService {
    Page<Field> findAll(Pageable pageable);

    Field findById(UUID id);

    Field save(FieldDTO fieldDTO);

    Field update(FieldDTO fieldDTO, UUID id);

    boolean delete(UUID id);
}
