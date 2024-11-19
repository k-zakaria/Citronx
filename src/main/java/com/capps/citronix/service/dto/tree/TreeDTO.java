package com.capps.citronix.service.dto.tree;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TreeDTO {
    @NotNull(message = "La date de plantation est obligatoire.")
    private LocalDate plantingDate;

    @NotNull(message = "La date de création est obligatoire.")
    private LocalDate createdAt;

    @NotNull(message = "L'ID du champ est obligatoire.")
    private UUID fieldId;

    private boolean isProductive;
}
