package com.capps.citronix.web.vm.harvestdetails;

import lombok.Data;

import java.util.UUID;

@Data
public class HarvestDetailsVM {
    private float quantity;
    private UUID harvestId;
    private UUID treeId;
}
