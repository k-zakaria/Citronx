package com.capps.citronix.service.dto.harvestdetails;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class HarvestDetailsDTO {
    @NotNull(message = "La quantité est obligatoire.")
    private float quantity;

    @NotNull(message = "L'ID de la récolte est obligatoire.")
    private UUID harvestId;

    @NotNull(message = "L'ID de l'arbre est obligatoire.")
    private UUID treeId;
}
