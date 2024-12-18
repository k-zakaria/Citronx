package com.capps.citronix.web.rest.controller;

import com.capps.citronix.domain.Tree;
import com.capps.citronix.service.TreeService;
import com.capps.citronix.web.vm.mapper.TreeMapper;
import com.capps.citronix.web.vm.request.TreeRequestVM;
import com.capps.citronix.web.vm.tree.TreeVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/trees")
@RequiredArgsConstructor
public class TreeController {
    private final TreeService service;
    private final TreeMapper mapper;

    @GetMapping
    public ResponseEntity<Page<TreeVM>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Tree> treePage = service.findAll(pageable);
        Page<TreeVM> vmPage = treePage.map(mapper::toVM);
        return ResponseEntity.ok(vmPage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreeVM> findById(@PathVariable UUID id) {
        Tree tree = service.findById(id);
        return ResponseEntity.ok(mapper.toVM(tree));
    }

    @PostMapping("/save")
    public ResponseEntity<TreeVM> save(@RequestBody @Valid TreeRequestVM treeRequestVM) {
        Tree tree = service.save(mapper.toEntity(treeRequestVM));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toVM(tree));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TreeVM> update(@PathVariable UUID id, @RequestBody @Valid TreeRequestVM treeRequestVM) {
        Tree updated = service.update(mapper.toEntity(treeRequestVM), id);
        return ResponseEntity.ok(mapper.toVM(updated));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.ok("Tree deleted successfully");
    }
}
