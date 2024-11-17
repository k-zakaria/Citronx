package com.capps.citronix.repository;

import com.capps.citronix.domain.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface FieldRepository extends JpaRepository<Field, UUID> {
    Page<Field> findAll(Pageable pageable);
}
