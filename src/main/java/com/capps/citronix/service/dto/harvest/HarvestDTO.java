package com.capps.citronix.service.dto.harvest;

import com.capps.citronix.domain.enums.Saison;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class HarvestDTO {

    @NotNull(message = "La date est obligatoire.")
    @PastOrPresent(message = "La date doit être passée ou actuelle.")
    private LocalDate date;

    @Positive(message = "La quantité totale doit être positive.")
    private float totalQuantity;

    @NotNull(message = "La saison est obligatoire.")
    private Saison saison;

    @NotNull(message = "Le champ est obligatoire.")
    private UUID fieldId;
}