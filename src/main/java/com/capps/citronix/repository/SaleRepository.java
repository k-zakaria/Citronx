package com.capps.citronix.repository;

import com.capps.citronix.domain.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaleRepository extends JpaRepository<Sale, UUID> {

}
