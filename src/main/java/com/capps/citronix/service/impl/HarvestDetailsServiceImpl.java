package com.capps.citronix.service.impl;

import com.capps.citronix.domain.Harvest;
import com.capps.citronix.domain.HarvestDetails;
import com.capps.citronix.domain.Tree;
import com.capps.citronix.repository.HarvestDetailsRepository;
import com.capps.citronix.repository.HarvestRepository;
import com.capps.citronix.repository.TreeRepository;
import com.capps.citronix.service.HarvestDetailsService;
import com.capps.citronix.web.errors.harvest.HarvestNotFoundException;
import com.capps.citronix.web.errors.harvestdetails.HarvestDetailsNotFoundException;
import com.capps.citronix.web.errors.harvestdetails.TreeAlreadyAssociatedException;
import com.capps.citronix.web.errors.tree.TreeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HarvestDetailsServiceImpl implements HarvestDetailsService {
    private final HarvestDetailsRepository repository;
    private final HarvestRepository harvestRepository;
    private final TreeRepository treeRepository;

    @Override
    public Page<HarvestDetails> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public HarvestDetails findById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new HarvestDetailsNotFoundException("HarvestDetails not found!"));
    }

    @Override
    public HarvestDetails save(HarvestDetails harvestDetails) {
        Harvest harvest = harvestRepository.findById(harvestDetails.getHarvest().getId())
                .orElseThrow(() -> new HarvestNotFoundException("Harvest not found!"));
        Tree tree = treeRepository.findById(harvestDetails.getTree().getId())
                .orElseThrow(() -> new TreeNotFoundException("Tree not Found !"));

        boolean exists = repository.existsByTreeAndHarvest_Saison(tree, harvest.getSaison());
        if (exists) {
            throw new TreeAlreadyAssociatedException("L'arbre est déjà associé à une autre récolte pour la saison " + harvest.getSaison());
        }

        harvestDetails.setHarvest(harvest);
        harvestDetails.setTree(tree);
        return repository.save(harvestDetails);
    }

    @Override
    public HarvestDetails update(HarvestDetails harvestDetails, UUID id) {
        HarvestDetails existing = repository.findById(id)
                .orElseThrow(() -> new HarvestDetailsNotFoundException("HarvestDetails not found!"));
        Harvest harvest = harvestRepository.findById(harvestDetails.getHarvest().getId())
                .orElseThrow(() -> new HarvestNotFoundException("Harvest not found!"));
        Tree tree = treeRepository.findById(harvestDetails.getTree().getId())
                .orElseThrow(() -> new TreeNotFoundException("Tree not Found !"));
        existing.setQuantity(harvestDetails.getQuantity());
        existing.setHarvest(harvest);
        existing.setTree(tree);

        return repository.save(existing);
    }

    @Override
    public boolean delete(UUID id) {
        HarvestDetails existing = repository.findById(id)
                .orElseThrow(() -> new HarvestDetailsNotFoundException("HarvestDetails not found!"));

        repository.delete(existing);
        return true;
    }
}
