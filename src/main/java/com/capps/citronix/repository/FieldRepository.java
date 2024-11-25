package com.capps.citronix.repository;

import com.capps.citronix.domain.Farm;
import com.capps.citronix.domain.Field;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface FieldRepository extends JpaRepository<Field, UUID> {

    @Query("SELECT COALESCE(SUM(f.area), 0) FROM Field f WHERE f.farm.id = :farmId")
    float sumAreaByFarm(@Param("farmId") UUID farmId);
    Page<Field> findAll(Pageable pageable);
    long countByFarm(Farm farm);
}
