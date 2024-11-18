package com.capps.citronix.repository;

import com.capps.citronix.domain.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface HarvestRepository extends JpaRepository<Harvest, UUID> {
    boolean existsByDateAndFieldId(LocalDate date, UUID fieldId);
}
