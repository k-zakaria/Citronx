package com.capps.citronix.service.impl;

import com.capps.citronix.domain.Farm;
import com.capps.citronix.repository.FarmRepository;
import com.capps.citronix.service.FarmService;
import com.capps.citronix.service.dto.farm.FarmDTO;
import com.capps.citronix.web.errors.farm.FarmExisteException;
import com.capps.citronix.web.errors.farm.FarmNotFoundException;
import com.capps.citronix.web.vm.mapper.FarmMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    private final FarmMapper farmMapper;

    @Override
    public Page<Farm> findAll(Pageable pageable) {
        return farmRepository.findAll(pageable);
    }

    @Override
    public Farm findById(UUID id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new FarmNotFoundException("Farm not found!"));
    }

    @Override
    public Farm save(Farm farm) {
        if (farm == null || farm.equals(new FarmDTO())){
            throw new FarmNotFoundException("Farm not found!");
        }
        if (farmRepository.existsByNameAndLocation(farm.getName(), farm.getLocation())) {
            throw new FarmExisteException("La ferme avec ce nom et cette localisation existe déjà.");
        }
        return farmRepository.save(farm);
    }

    @Override
    public Farm update(Farm farm, UUID id) {
        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(() -> new FarmNotFoundException("Farm not found!"));
        existingFarm.setName(farm.getName());
        existingFarm.setLocation(farm.getLocation());
        existingFarm.setArea(farm.getArea());
        existingFarm.setCreatedAt(farm.getCreatedAt());

        return farmRepository.save(existingFarm);
    }

    @Override
    public boolean delete(UUID id) {
        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(() -> new FarmNotFoundException("Farm not found!"));
         farmRepository.delete(existingFarm);
         return true;
    }
}
