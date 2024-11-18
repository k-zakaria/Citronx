package com.capps.citronix.web.vm.tree;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TreeVM {
    private UUID id;
    private LocalDate plantingDate;
    private LocalDate createdAt;
    private UUID fieldId;
    private int age; // Calculé dans l'entité Tree
}
