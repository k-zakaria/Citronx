package com.capps.citronix.service.dto.sale;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class SaleDTO {
    @NotNull(message = "La date de la vente est obligatoire.")
    private LocalDate date;

    @NotNull(message = "Le prix unitaire est obligatoire.")
    private BigDecimal unitPrice;

    @NotNull(message = "La quantité est obligatoire.")
    private float quantity;

    @NotNull(message = "Le nom du client est obligatoire.")
    private String clientName;

    @NotNull(message = "L'ID de la récolte est obligatoire.")
    private UUID harvestId;
}
