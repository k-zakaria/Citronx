package com.capps.citronix.web.rest.controller;

import com.capps.citronix.domain.Field;
import com.capps.citronix.service.FieldService;
import com.capps.citronix.service.dto.field.FieldDTO;
import com.capps.citronix.web.response.ResponseHandler;
import com.capps.citronix.web.vm.field.FieldVM;
import com.capps.citronix.web.vm.mapper.FieldMapper;
import com.capps.citronix.web.vm.request.FieldRequestVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("api/fields")
@RequiredArgsConstructor
public class FieldController {
    private final FieldService fieldService;
    private final FieldMapper fieldMapper;

    @GetMapping
    public ResponseEntity<Page<FieldVM>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Field> fieldPage = fieldService.findAll(pageable);
        Page<FieldVM> fieldVMPage = fieldPage.map(fieldMapper::toVM);
        return ResponseEntity.ok(fieldVMPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id) {
        Field field = fieldService.findById(id);
        FieldVM fieldVM = fieldMapper.toVM(field);
        return ResponseHandler.responseBuilder("Requested Field", HttpStatus.OK, fieldVM);
    }

    @PostMapping("/save")
    public ResponseEntity<FieldVM> save(@RequestBody @Valid FieldRequestVM fieldRequestVM) {
        Field field = fieldService.save(fieldMapper.toFieldEntity(fieldRequestVM));
        FieldVM fieldVM = fieldMapper.toVM(field);
        return ResponseEntity.status(HttpStatus.CREATED).body(fieldVM);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FieldVM> update(@PathVariable UUID id, @RequestBody @Valid FieldRequestVM fieldRequestVM) {
        Field field = fieldService.update(fieldMapper.toFieldEntity(fieldRequestVM), id);
        FieldVM fieldVM = fieldMapper.toVM(field);
        return ResponseEntity.ok(fieldVM);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        fieldService.delete(id);
        return ResponseEntity.ok("Field deleted");
    }
}
