package com.capps.citronix.repository;

import com.capps.citronix.domain.HarvestDetails;
import com.capps.citronix.domain.Tree;
import com.capps.citronix.domain.enums.Saison;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HarvestDetailsRepository extends JpaRepository<HarvestDetails, UUID> {
    boolean existsByTreeAndHarvest_Saison(Tree tree, Saison saison);
}
