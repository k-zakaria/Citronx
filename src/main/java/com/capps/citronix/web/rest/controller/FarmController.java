package com.capps.citronix.web.rest.controller;

import com.capps.citronix.domain.Farm;
import com.capps.citronix.service.FarmService;
import com.capps.citronix.service.dto.FarmSearchCriteria;
import com.capps.citronix.web.response.ResponseHandler;
import com.capps.citronix.web.vm.farm.FarmVM;
import com.capps.citronix.web.vm.mapper.FarmMapper;
import com.capps.citronix.web.vm.request.FarmRequestVM;
import com.capps.citronix.web.vm.request.FarmSearchCriteriaRequestVM;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/farms")
@RequiredArgsConstructor
public class FarmController {
    private final FarmService farmService;
    private final FarmMapper farmMapper;

    @GetMapping
    public ResponseEntity<Page<FarmVM>> findAll(
            @RequestParam(defaultValue = "0" ) int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        Page<Farm> farmPage = farmService.findAll(pageable);
        Page<FarmVM> farmVMPage = farmPage.map(farmMapper::toVM);
        return ResponseEntity.ok(farmVMPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Farm farm = farmService.findById(id);
        FarmVM farmVM = farmMapper.toVM(farm);
        return ResponseHandler.responseBuilder("Reuquested Farm", HttpStatus.OK, farmVM);
    }

    @PostMapping("/save")
    public ResponseEntity<FarmVM> save(@RequestBody FarmRequestVM farmRequestVM){
        Farm farm = farmService.save(farmMapper.toEntity(farmRequestVM));
        FarmVM farmVM = farmMapper.toVM(farm);
        return ResponseEntity.status(HttpStatus.CREATED).body(farmVM);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FarmVM> update(@PathVariable UUID id, @RequestBody FarmRequestVM farmRequestVM){
        Farm farm = farmService.update(farmMapper.toEntity(farmRequestVM), id);
        FarmVM farmVM = farmMapper.toVM(farm);
        return ResponseEntity.ok(farmVM);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id){
        farmService.delete(id);
        return ResponseEntity.ok("Participation deleted");

    }

    @PostMapping("/farms/search")
    public ResponseEntity<List<FarmVM>> searchFarms(@RequestBody FarmSearchCriteriaRequestVM criteria) {
        FarmSearchCriteria farmSearchCriteria = farmMapper.toDTO(criteria);
        List<Farm> farms = farmService.searchFarms(farmSearchCriteria);
        List<FarmVM> farmVMPage = farms.stream()
                .map(farmMapper::toVM)
                .toList();
        return ResponseEntity.ok(farmVMPage);
    }



}   
