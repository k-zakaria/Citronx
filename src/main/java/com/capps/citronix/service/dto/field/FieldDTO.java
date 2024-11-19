package com.capps.citronix.service.dto.field;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.UUID;

@Data
public class FieldDTO {
    @Positive(message = "La superficie doit être un nombre positif.")
    @Min(value = 1000, message = "La superficie d'un champ doit être au minimum de 1000 m² (0.1 hectare).")
    private float area;

    @NotNull(message = "L'identifiant de la ferme est obligatoire.")
    private UUID farmId;
}