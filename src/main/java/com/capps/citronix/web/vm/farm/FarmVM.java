package com.capps.citronix.web.vm.farm;

import lombok.Data;

import java.util.UUID;

@Data
public class FarmVM {
    private UUID id;
    private String name;
    private String location;
    private float area;
    private int numberOfFields;
}
