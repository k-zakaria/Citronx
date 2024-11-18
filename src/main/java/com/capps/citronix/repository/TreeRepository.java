package com.capps.citronix.repository;

import com.capps.citronix.domain.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TreeRepository extends JpaRepository<Tree, UUID> {
    // Méthodes spécifiques si nécessaires
}
