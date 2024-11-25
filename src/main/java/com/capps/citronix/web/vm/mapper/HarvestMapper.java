package com.capps.citronix.web.vm.mapper;

import com.capps.citronix.domain.Harvest;
import com.capps.citronix.web.vm.harvest.HarvestVM;
import com.capps.citronix.web.vm.request.HarvestRequestVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestMapper {

    @Mapping(source = "fieldId", target = "field.id")
    Harvest toEntity(HarvestRequestVM harvestRequestVM);

    @Mapping(expression = "java(harvest.getField() != null ? harvest.getField().getId().toString() : null)", target = "field")
    HarvestVM toVM(Harvest harvest);
}
