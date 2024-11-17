package com.capps.citronix.web.vm.field;

import lombok.Data;

@Data
public class FieldVM {
    private float area;
    private String farmName;
    private int numberOfTrees;
    private boolean hasHarvest;
}