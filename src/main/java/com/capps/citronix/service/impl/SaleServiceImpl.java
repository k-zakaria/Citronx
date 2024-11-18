package com.capps.citronix.service.impl;

import com.capps.citronix.domain.Sale;
import com.capps.citronix.repository.SaleRepository;
import com.capps.citronix.service.SaleService;
import com.capps.citronix.service.dto.sale.SaleDTO;
import com.capps.citronix.web.errors.sale.SaleNotFoundException;
import com.capps.citronix.web.vm.mapper.SaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {
    private final SaleRepository repository;
    private final SaleMapper mapper;

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
    public Sale save(SaleDTO saleDTO) {
        Sale sale = mapper.toEntity(saleDTO);
        return repository.save(sale);
    }

    @Override
    public Sale update(SaleDTO saleDTO, UUID id) {
        Sale existing = repository.findById(id)
                .orElseThrow(() -> new SaleNotFoundException("Sale not found!"));

        existing.setDate(saleDTO.getDate());
        existing.setUnitPrice(saleDTO.getUnitPrice());
        existing.setQuantity(saleDTO.getQuantity());
        existing.setClientName(saleDTO.getClientName());
        existing.getHarvest().setId(saleDTO.getHarvestId());

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
