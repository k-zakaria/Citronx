package com.capps.citronix.web.vm.farm;

import lombok.Data;

@Data
public class FarmVM {
    private String name;
    private String location;
    private float area;
    private int numberOfFields;
}
