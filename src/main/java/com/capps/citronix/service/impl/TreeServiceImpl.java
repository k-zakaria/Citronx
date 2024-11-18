package com.capps.citronix.service.impl;

import com.capps.citronix.domain.Field;
import com.capps.citronix.domain.Tree;
import com.capps.citronix.repository.FieldRepository;
import com.capps.citronix.repository.TreeRepository;
import com.capps.citronix.service.TreeService;
import com.capps.citronix.service.dto.tree.TreeDTO;
import com.capps.citronix.web.errors.field.FieldNotFoundException;
import com.capps.citronix.web.errors.tree.TreeNotFoundException;
import com.capps.citronix.web.vm.mapper.TreeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TreeServiceImpl implements TreeService {
    private final TreeRepository repository;
    private final FieldRepository fieldRepository;
    private final TreeMapper mapper;

    @Override
    public Page<Tree> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Tree findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new TreeNotFoundException("Tree not found!"));
    }

    @Override
    public Tree save(TreeDTO treeDTO) {
        Field field = fieldRepository.findById(treeDTO.getFieldId())
                .orElseThrow(() -> new FieldNotFoundException("Field not found!"));
        Tree tree = mapper.toEntity(treeDTO);
        tree.setField(field);
        return repository.save(tree);
    }

    @Override
    public Tree update(TreeDTO treeDTO, UUID id) {
        Tree existing = repository.findById(id)
                .orElseThrow(() -> new TreeNotFoundException("Tree not found!"));
        Field field = fieldRepository.findById(treeDTO.getFieldId())
                .orElseThrow(() -> new FieldNotFoundException("Field not found!"));

        existing.setPlantingDate(treeDTO.getPlantingDate());
        existing.setCreatedAt(treeDTO.getCreatedAt());
        existing.setField(field);

        return repository.save(existing);
    }

    @Override
    public boolean delete(UUID id) {
        Tree existing = repository.findById(id)
                .orElseThrow(() -> new TreeNotFoundException("Tree not found!"));

        repository.delete(existing);
        return true;
    }
}
