package com.capps.citronix.web.rest.controller;

import com.capps.citronix.domain.HarvestDetails;
import com.capps.citronix.service.HarvestDetailsService;
import com.capps.citronix.web.vm.harvestdetails.HarvestDetailsVM;
import com.capps.citronix.web.vm.mapper.HarvestDetailsMapper;
import com.capps.citronix.web.vm.request.HarvestDetailsRequestVM;
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

    @GetMapping
    public ResponseEntity<Page<HarvestDetailsVM>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HarvestDetails> detailsPage = service.findAll(pageable);
        Page<HarvestDetailsVM> vmPage = detailsPage.map(mapper::toVM);
        return ResponseEntity.ok(vmPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HarvestDetailsVM> findById(@PathVariable UUID id) {
        HarvestDetails details = service.findById(id);
        return ResponseEntity.ok(mapper.toVM(details));
    }

    @PostMapping("/save")
    public ResponseEntity<HarvestDetailsVM> save(@RequestBody HarvestDetailsRequestVM harvestDetailsRequestVM) {
        HarvestDetails details = service.save(mapper.toEntity(harvestDetailsRequestVM));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toVM(details));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<HarvestDetailsVM> update(@PathVariable UUID id, @RequestBody HarvestDetailsRequestVM harvestDetailsRequestVM) {
        HarvestDetails updated = service.update(mapper.toEntity(harvestDetailsRequestVM), id);
        return ResponseEntity.ok(mapper.toVM(updated));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok("HarvestDetails deleted successfully");
    }
}
