package com.capps.citronix.repository;

import com.capps.citronix.domain.Farm;
import com.capps.citronix.domain.Field;
import com.capps.citronix.domain.Tree;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TreeRepository extends JpaRepository<Tree, UUID> {
    Page<Tree> findAll(Pageable pageable);
    long countByField(Field field);
}
