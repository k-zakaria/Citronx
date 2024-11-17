package com.capps.citronix.repository;

import com.capps.citronix.domain.Farm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FarmRepository extends JpaRepository<Farm, UUID> {
    boolean existsByNameAndLocation(String name, String location);
    Page<Farm> findAll(Pageable pageable);
}
