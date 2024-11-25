package com.capps.citronix.service.impl;

import com.capps.citronix.domain.Farm;
import com.capps.citronix.domain.Field;
import com.capps.citronix.repository.FarmRepository;
import com.capps.citronix.repository.FieldRepository;
import com.capps.citronix.web.errors.farm.FarmNotFoundException;
import com.capps.citronix.web.errors.field.AreaConsistencyException;
import com.capps.citronix.web.errors.field.FieldNotFoundException;
import com.capps.citronix.web.errors.field.MaxFieldAreaExceededException;
import com.capps.citronix.web.errors.field.MaxFieldsExceededException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FieldServiceImplTest {

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private FarmRepository farmRepository;

    @InjectMocks
    private FieldServiceImpl fieldService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById_FieldExists() {
        UUID fieldId = UUID.randomUUID();
        Field mockField = new Field();
        mockField.setId(fieldId);

        when(fieldRepository.findById(fieldId)).thenReturn(Optional.of(mockField));

        Field result = fieldService.findById(fieldId);

        assertNotNull(result);
        assertEquals(fieldId, result.getId());
        verify(fieldRepository, times(1)).findById(fieldId);
    }

    @Test
    void testFindById_FieldDoesNotExist() {
        UUID fieldId = UUID.randomUUID();

        when(fieldRepository.findById(fieldId)).thenReturn(Optional.empty());

        assertThrows(FieldNotFoundException.class, () -> fieldService.findById(fieldId));
        verify(fieldRepository, times(1)).findById(fieldId);
    }

    @Test
    void testSave_ValidField() {
        UUID farmId = UUID.randomUUID();
        Farm farm = new Farm();
        farm.setId(farmId);
        farm.setArea(1000);

        Field field = new Field();
        field.setArea(100);
        field.setFarm(farm);

        when(farmRepository.findById(farmId)).thenReturn(Optional.of(farm));
        when(fieldRepository.sumAreaByFarm(farmId)).thenReturn(200f);
        when(fieldRepository.countByFarm(farm)).thenReturn(5L);
        when(fieldRepository.save(field)).thenReturn(field);

        Field result = fieldService.save(field);

        assertNotNull(result);
        verify(farmRepository, times(1)).findById(farmId);
        verify(fieldRepository, times(1)).save(field);
    }

    @Test
    void testSave_ExceedsMaxFieldArea() {
        UUID farmId = UUID.randomUUID();
        Farm farm = new Farm();
        farm.setId(farmId);
        farm.setArea(1000);

        Field field = new Field();
        field.setArea(600);
        field.setFarm(farm);

        when(farmRepository.findById(farmId)).thenReturn(Optional.of(farm));

        assertThrows(MaxFieldAreaExceededException.class, () -> fieldService.save(field));
        verify(farmRepository, times(1)).findById(farmId);
        verify(fieldRepository, never()).save(any());
    }

    @Test
    void testSave_FarmNotFoundException() {
        // Arrange
        Field field = new Field();
        Farm farm = new Farm(); // Utilisation d'un constructeur sans paramètre, si disponible
        farm.setId(UUID.randomUUID());

        // Simuler que la ferme n'existe pas dans la base de données
        when(farmRepository.findById(any(UUID.class))).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(FarmNotFoundException.class, () -> fieldService.save(field));
    }

    @Test
    void testSave_AreaConsistencyException() {
        // Arrange
        Field field = new Field();
        field.setArea(500); // Exemple de superficie pour le champ
        Farm farm = new Farm(); // Utilisation d'un constructeur sans paramètre, si disponible
        farm.setId(UUID.randomUUID());
        farm.setArea(1000); // Superficie totale de la ferme

        field.setFarm(farm);

        // Simuler que la ferme existe
        when(farmRepository.findById(farm.getId())).thenReturn(Optional.of(farm));
        // Simuler la somme des superficies des champs existants
        when(fieldRepository.sumAreaByFarm(farm.getId())).thenReturn(600f); // Somme des superficies des champs existants

        // Act & Assert
        assertThrows(AreaConsistencyException.class, () -> fieldService.save(field));
    }


    @Test
    void testSave_MaxFieldAreaExceededException() {
        // Arrange
        Field field = new Field();
        field.setArea(600); // Exemple de superficie du champ
        Farm farm = new Farm(); // Utilisation d'un constructeur sans paramètre, si disponible
        farm.setId(UUID.randomUUID()); // Initialiser l'ID avec un UUID
        farm.setArea(1000); // Superficie totale de la ferme

        field.setFarm(farm);

        // Simuler que la ferme existe
        when(farmRepository.findById(farm.getId())).thenReturn(Optional.of(farm));

        // Act & Assert
        assertThrows(MaxFieldAreaExceededException.class, () -> fieldService.save(field));
    }


    @Test
    void testSave_MaxFieldsExceededException() {
        // Arrange
        Field field = new Field();
        field.setArea(200); // Exemple de superficie du champ
        Farm farm = new Farm(); // Utilisation d'un constructeur sans paramètre, si disponible
        farm.setId(UUID.randomUUID()); // Initialiser l'ID avec un UUID
        farm.setArea(1000); // Superficie totale de la ferme

        field.setFarm(farm);

        // Simuler que la ferme existe
        when(farmRepository.findById(farm.getId())).thenReturn(Optional.of(farm));
        // Simuler que la ferme a déjà 10 champs
        when(fieldRepository.countByFarm(farm)).thenReturn(10L);

        // Act & Assert
        assertThrows(MaxFieldsExceededException.class, () -> fieldService.save(field));
    }

    @Test
    void testSave_Success() {
        // Arrange
        Field field = new Field();
        field.setArea(200); // Exemple de superficie du champ
        Farm farm = new Farm(); // Utilisation d'un constructeur sans paramètre, si disponible
        farm.setId(UUID.randomUUID()); // Initialiser l'ID avec un UUID

        farm.setArea(1000); // Superficie totale de la ferme

        field.setFarm(farm);

        // Simuler que la ferme existe
        when(farmRepository.findById(farm.getId())).thenReturn(Optional.of(farm));
        // Simuler que la somme des superficies des champs existants est inférieure à la superficie totale de la ferme
        when(fieldRepository.sumAreaByFarm(farm.getId())).thenReturn(500f);
        // Simuler que la ferme a moins de 10 champs
        when(fieldRepository.countByFarm(farm)).thenReturn(5L);

        // Simuler que la sauvegarde fonctionne
        when(fieldRepository.save(any(Field.class))).thenReturn(field);

        // Act
        Field savedField = fieldService.save(field);

        // Assert
        assertNotNull(savedField);
        assertEquals(field.getArea(), savedField.getArea());
        assertEquals(field.getFarm(), savedField.getFarm());
    }


    @Test
    void testDelete_ExistingField() {
        UUID fieldId = UUID.randomUUID();
        Field field = new Field();
        field.setId(fieldId);

        when(fieldRepository.findById(fieldId)).thenReturn(Optional.of(field));

        boolean result = fieldService.delete(fieldId);

        assertTrue(result);
        verify(fieldRepository, times(1)).findById(fieldId);
        verify(fieldRepository, times(1)).delete(field);
    }

    @Test
    void testDelete_NonExistingField() {
        UUID fieldId = UUID.randomUUID();

        when(fieldRepository.findById(fieldId)).thenReturn(Optional.empty());

        assertThrows(FieldNotFoundException.class, () -> fieldService.delete(fieldId));
        verify(fieldRepository, times(1)).findById(fieldId);
        verify(fieldRepository, never()).delete(any());
    }
}
