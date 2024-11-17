package com.capps.citronix.service.dto.farm;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
public class FarmDTO {
    @NotBlank(message = "Le nom de la ferme est obligatoire.")
    @Size(min = 3, max = 100, message = "Le nom de la ferme doit contenir entre 3 et 100 caractères.")
    private String name;

    @NotBlank(message = "La localisation de la ferme est obligatoire.")
    @Size(max = 255, message = "La localisation ne doit pas dépasser 255 caractères.")
    private String location;

    @Positive(message = "La superficie doit être un nombre positif.")
    private float area;

    @NotNull(message = "La date de création est obligatoire.")
    @PastOrPresent(message = "La date de création doit être une date passée ou actuelle.")
    private LocalDate createdAt;
}
