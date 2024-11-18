package com.capps.citronix.service.impl;

import com.capps.citronix.domain.HarvestDetails;
import com.capps.citronix.repository.HarvestDetailsRepository;
import com.capps.citronix.service.HarvestDetailsService;
import com.capps.citronix.web.vm.mapper.HarvestDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HarvestDetailsServiceImpl implements HarvestDetailsService {
    private final HarvestDetailsRepository repository;
    private final HarvestDetailsMapper mapper;


    @Override
    public HarvestDetails save(com.capps.citronix.service.dto.harvestdetails.HarvestDetailsDTO harvestDetailsDTO) {
        HarvestDetails harvestDetails = mapper.toEntity(harvestDetailsDTO);
        return repository.save(harvestDetails);
    }
}
