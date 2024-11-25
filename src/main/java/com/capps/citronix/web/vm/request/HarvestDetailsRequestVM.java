package com.capps.citronix.web.vm.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class HarvestDetailsRequestVM {
    @NotNull(message = "La quantité est obligatoire.")
    private float quantity;

    @NotNull(message = "L'ID de la récolte est obligatoire.")
    private UUID harvestId;

    @NotNull(message = "L'ID de l'arbre est obligatoire.")
    private UUID treeId;
}
