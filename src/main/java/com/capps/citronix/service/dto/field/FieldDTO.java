package com.capps.citronix.service.dto.field;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Data
public class FieldDTO {
    @Positive(message = "La superficie doit être un nombre positif.")
    private float area;

    @NotNull(message = "L'identifiant de la ferme est obligatoire.")
    private UUID farmId;
}