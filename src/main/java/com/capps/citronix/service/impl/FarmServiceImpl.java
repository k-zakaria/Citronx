package com.capps.citronix.service.impl;

import com.capps.citronix.domain.Farm;
import com.capps.citronix.repository.FarmRepository;
import com.capps.citronix.service.FarmService;
import com.capps.citronix.service.dto.FarmSearchCriteria;
import com.capps.citronix.web.errors.farm.FarmExisteException;
import com.capps.citronix.web.errors.farm.FarmNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<Farm> findAll(Pageable pageable) {
        return farmRepository.findAll(pageable);
    }

    @Override
    public Farm findById(UUID id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new FarmNotFoundException("Farm not found!"));
    }

    @Override
    public Farm save(Farm farm) {
        if (farm == null || farm.equals(new Farm())){
            throw new FarmNotFoundException("Farm not found!");
        }
        if (farmRepository.existsByNameAndLocation(farm.getName(), farm.getLocation())) {
            throw new FarmExisteException("La ferme avec ce nom et cette localisation existe déjà.");
        }
        return farmRepository.save(farm);
    }

    @Override
    public Farm update(Farm farm, UUID id) {
        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(() -> new FarmNotFoundException("Farm not found!"));
        existingFarm.setName(farm.getName());
        existingFarm.setLocation(farm.getLocation());
        existingFarm.setArea(farm.getArea());
        existingFarm.setCreatedAt(farm.getCreatedAt());

        return farmRepository.save(existingFarm);
    }

    @Override
    public boolean delete(UUID id) {
        Farm existingFarm = farmRepository.findById(id)
                .orElseThrow(() -> new FarmNotFoundException("Farm not found!"));
         farmRepository.delete(existingFarm);
         return true;
    }

    @Override
    public List<Farm> searchFarms(FarmSearchCriteria criteria) {
        // Création du CriteriaBuilder et du CriteriaQuery
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Farm> query = cb.createQuery(Farm.class);
        Root<Farm> root = query.from(Farm.class);

        // Liste des prédicats (conditions)
        List<Predicate> predicates = new ArrayList<>();

        // Filtrer par nom
        if (criteria.getName() != null && !criteria.getName().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + criteria.getName().toLowerCase() + "%"));
        }

        // Filtrer par localisation
        if (criteria.getLocation() != null && !criteria.getLocation().isEmpty()) {
            predicates.add(cb.like(cb.lower(root.get("location")), "%" + criteria.getLocation().toLowerCase() + "%"));
        }

        // Filtrer par superficie minimale
        if (criteria.getMinArea() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("area"), criteria.getMinArea()));
        }

        // Filtrer par superficie maximale
        if (criteria.getMaxArea() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("area"), criteria.getMaxArea()));
        }

        // Filtrer par date de création après une certaine date
        if (criteria.getCreatedAfter() != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("createdAt"), criteria.getCreatedAfter()));
        }

        // Filtrer par date de création avant une certaine date
        if (criteria.getCreatedBefore() != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("createdAt"), criteria.getCreatedBefore()));
        }

        // Appliquer les prédicats au query
        query.where(predicates.toArray(new Predicate[0]));

        // Exécuter la requête
        return entityManager.createQuery(query).getResultList();
    }

}
