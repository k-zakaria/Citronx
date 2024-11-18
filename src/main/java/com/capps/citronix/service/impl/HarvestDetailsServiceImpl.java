package com.capps.citronix.service.impl;

import com.capps.citronix.domain.HarvestDetails;
import com.capps.citronix.repository.HarvestDetailsRepository;
import com.capps.citronix.service.HarvestDetailsService;
import com.capps.citronix.service.dto.harvestdetails.HarvestDetailsDTO;
import com.capps.citronix.web.errors.harvestdetails.HarvestDetailsNotFoundException;
import com.capps.citronix.web.vm.mapper.HarvestDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HarvestDetailsServiceImpl implements HarvestDetailsService {
    private final HarvestDetailsRepository repository;
    private final HarvestDetailsMapper mapper;

    @Override
    public Page<HarvestDetails> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public HarvestDetails findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new HarvestDetailsNotFoundException("HarvestDetails not found!"));
    }

    @Override
    public HarvestDetails save(HarvestDetailsDTO harvestDetailsDTO) {
        HarvestDetails harvestDetails = mapper.toEntity(harvestDetailsDTO);
        return repository.save(harvestDetails);
    }

    @Override
    public HarvestDetails update(HarvestDetailsDTO harvestDetailsDTO, UUID id) {
        HarvestDetails existing = repository.findById(id)
                .orElseThrow(() -> new HarvestDetailsNotFoundException("HarvestDetails not found!"));

        existing.setQuantity(harvestDetailsDTO.getQuantity());
        existing.getHarvest().setId(harvestDetailsDTO.getHarvestId());
        existing.getTree().setId(harvestDetailsDTO.getTreeId());

        return repository.save(existing);
    }

    @Override
    public boolean delete(UUID id) {
        HarvestDetails existing = repository.findById(id)
                .orElseThrow(() -> new HarvestDetailsNotFoundException("HarvestDetails not found!"));

        repository.delete(existing);
        return true;
    }
}
