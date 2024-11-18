package com.capps.citronix.web.rest.controller;

import com.capps.citronix.domain.HarvestDetails;
import com.capps.citronix.service.HarvestDetailsService;
import com.capps.citronix.service.dto.harvestdetails.HarvestDetailsDTO;
import com.capps.citronix.web.vm.harvestdetails.HarvestDetailsVM;
import com.capps.citronix.web.vm.mapper.HarvestDetailsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/harvest-details")
@RequiredArgsConstructor
public class HarvestDetailsController {
    private final HarvestDetailsService service;
    private final HarvestDetailsMapper mapper;

    @PostMapping
    public ResponseEntity<HarvestDetailsVM> save(@RequestBody HarvestDetailsDTO dto) {
        HarvestDetails details = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toVM(details));
    }
}
