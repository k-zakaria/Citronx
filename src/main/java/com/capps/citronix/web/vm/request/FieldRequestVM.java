package com.capps.citronix.web.vm.request;

import com.capps.citronix.domain.Farm;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.mapstruct.Mapping;

import java.util.UUID;

@Data
public class FieldRequestVM {
    @Positive(message = "La superficie doit être un nombre positif.")
    private float area;

    @NotNull(message = "L'identifiant de la ferme est obligatoire.")
    private UUID farmId;
}
