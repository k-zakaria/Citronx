package com.capps.citronix.web.vm.sale;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class SaleVM {
    private LocalDate date;
    private BigDecimal unitPrice;
    private float quantity;
    private String clientName;
    private UUID harvestId;
}
