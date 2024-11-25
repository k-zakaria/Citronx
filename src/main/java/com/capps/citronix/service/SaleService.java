package com.capps.citronix.service;

import com.capps.citronix.domain.Sale;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface SaleService {
    Page<Sale> findAll(Pageable pageable);

    Sale findById(UUID id);

    Sale save(Sale sale);

    Sale update(Sale sale, UUID id);

    boolean delete(UUID id);
}
