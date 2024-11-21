package com.capps.citronix.web.rest.controller;

import com.capps.citronix.domain.Harvest;
import com.capps.citronix.service.HarvestService;
import com.capps.citronix.service.dto.harvest.HarvestDTO;
import com.capps.citronix.web.vm.harvest.HarvestVM;
import com.capps.citronix.web.vm.mapper.HarvestMapper;
import com.capps.citronix.web.vm.request.HarvestRequestVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/harvests")
@RequiredArgsConstructor
public class HarvestController {
    private final HarvestService harvestService;
    private final HarvestMapper harvestMapper;

    @GetMapping
    public ResponseEntity<List<HarvestVM>> findAll() {
        List<HarvestVM> harvests = harvestService.findAll().stream()
                .map(harvestMapper::toVM)
                .collect(Collectors.toList());
        return ResponseEntity.ok(harvests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestVM> findById(@PathVariable UUID id) {
        Harvest harvest = harvestService.findById(id);
        return ResponseEntity.ok(harvestMapper.toVM(harvest));
    }

    @PostMapping("/save")
    public ResponseEntity<HarvestVM> create(@RequestBody HarvestRequestVM harvestRequestVM) {
        HarvestDTO harvestDTO = harvestMapper.toDTO(harvestRequestVM);
        Harvest harvest = harvestService.save(harvestDTO);
        return ResponseEntity.ok(harvestMapper.toVM(harvest));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HarvestVM> update(@PathVariable UUID id, @RequestBody @Valid HarvestRequestVM harvestRequestVM) {
        HarvestDTO harvestDTO = harvestMapper.toDTO(harvestRequestVM);
        Harvest harvest = harvestService.update(id, harvestDTO);
        return ResponseEntity.ok(harvestMapper.toVM(harvest));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        harvestService.delete(id);
        return ResponseEntity.ok("Harvest deleted successfully");
    }
}
