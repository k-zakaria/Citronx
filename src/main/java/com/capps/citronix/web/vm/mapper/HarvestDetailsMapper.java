package com.capps.citronix.web.vm.mapper;

import com.capps.citronix.domain.HarvestDetails;
import com.capps.citronix.web.vm.harvestdetails.HarvestDetailsVM;
import com.capps.citronix.web.vm.request.HarvestDetailsRequestVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestDetailsMapper {
    @Mapping(source = "harvest.id", target = "harvestId")
    @Mapping(source = "tree.id", target = "treeId")
    HarvestDetailsVM toVM(HarvestDetails harvestDetails);

    @Mapping(source = "harvestId", target = "harvest.id")
    @Mapping(source = "treeId", target = "tree.id")
    HarvestDetails toEntity(HarvestDetailsRequestVM harvestDetailsRequestVM);

}
