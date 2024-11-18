package com.capps.citronix.web.vm.harvest;

import com.capps.citronix.domain.enums.Saison;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HarvestVM {
    private LocalDate date;
    private float totalQuantity;
    private Saison saison;
    private String fieldName;
}
