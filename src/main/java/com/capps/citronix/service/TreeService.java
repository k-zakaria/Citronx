package com.capps.citronix.service;

import com.capps.citronix.domain.Tree;
import com.capps.citronix.service.dto.tree.TreeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface TreeService {
    Page<Tree> findAll(Pageable pageable);

    Tree findById(UUID id);

    Tree save(Tree tree);

    Tree update(Tree tree, UUID id);

    boolean delete(UUID id);
}
