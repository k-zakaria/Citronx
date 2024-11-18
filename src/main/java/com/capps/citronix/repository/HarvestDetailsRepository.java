package com.capps.citronix.repository;

import com.capps.citronix.domain.HarvestDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface HarvestDetailsRepository extends JpaRepository<HarvestDetails, UUID> {

}
