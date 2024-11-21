package com.capps.citronix.web.rest.controller;

import com.capps.citronix.domain.Sale;
import com.capps.citronix.service.SaleService;
import com.capps.citronix.service.dto.sale.SaleDTO;
import com.capps.citronix.web.vm.mapper.SaleMapper;
import com.capps.citronix.web.vm.request.SaleRequestVM;
import com.capps.citronix.web.vm.sale.SaleVM;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/sales")
@RequiredArgsConstructor
public class SaleController {
    private final SaleService service;
    private final SaleMapper mapper;

    @GetMapping
    public ResponseEntity<Page<SaleVM>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Sale> salePage = service.findAll(pageable);
        Page<SaleVM> vmPage = salePage.map(mapper::toVM);
        return ResponseEntity.ok(vmPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleVM> findById(@PathVariable UUID id) {
        Sale sale = service.findById(id);
        return ResponseEntity.ok(mapper.toVM(sale));
    }

    @PostMapping("/save")
    public ResponseEntity<SaleVM> save(@RequestBody SaleRequestVM saleRequestVM) {
        Sale sale = service.save(mapper.toEntity(saleRequestVM));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toVM(sale));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<SaleVM> update(@PathVariable UUID id, @RequestBody SaleRequestVM saleRequestVM) {
        Sale updated = service.update(mapper.toEntity(saleRequestVM), id);
        return ResponseEntity.ok(mapper.toVM(updated));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok("Sale deleted successfully");
    }
}
