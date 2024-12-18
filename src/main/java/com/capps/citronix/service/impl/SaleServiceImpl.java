package com.capps.citronix.service.impl;

import com.capps.citronix.domain.Harvest;
import com.capps.citronix.domain.Sale;
import com.capps.citronix.repository.HarvestRepository;
import com.capps.citronix.repository.SaleRepository;
import com.capps.citronix.service.SaleService;
import com.capps.citronix.web.errors.harvest.HarvestNotFoundException;
import com.capps.citronix.web.errors.sale.SaleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository repository;
    private final HarvestRepository harvestRepository;


    @Override
    public Page<Sale> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Sale findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found!"));
    }

    @Override
    public Sale save(Sale sale) {

        Harvest harvest = harvestRepository.findById(sale.getHarvest().getId())
                .orElseThrow(() -> new HarvestNotFoundException("Harvest not found!"));

        BigDecimal revenue = sale.getUnitPrice()
                .multiply(BigDecimal.valueOf(harvest.getTotalQuantity()));

        sale.setQuantity(revenue);
        sale.setDate(LocalDate.now());
        sale.setHarvest(harvest);

        return repository.save(sale);
    }


    @Override
    public Sale update(Sale sale, UUID id) {
        Sale existing = repository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found!"));
        Harvest harvest = harvestRepository.findById(sale.getHarvest().getId())
                .orElseThrow(() -> new HarvestNotFoundException("Harvest not found!"));
        BigDecimal revenue = sale.getUnitPrice()
                .multiply(BigDecimal.valueOf(harvest.getTotalQuantity()));


        existing.setDate(sale.getDate());
        existing.setUnitPrice(sale.getUnitPrice());
        existing.setQuantity(revenue);
        existing.setClientName(sale.getClientName());
        existing.setHarvest(harvest);

        return repository.save(existing);
    }

    @Override
    public boolean delete(UUID id) {
        Sale existing = repository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found!"));

        repository.delete(existing);
        return true;
    }
}
