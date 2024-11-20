package com.capps.citronix.web.vm.mapper;

import com.capps.citronix.domain.Harvest;
import com.capps.citronix.service.dto.harvest.HarvestDTO;
import com.capps.citronix.web.vm.harvest.HarvestVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
    Harvest toEntity(HarvestDTO harvestDTO);

    @Mapping(expression = "java(harvest.getField() != null ? harvest.getField().getId().toString() : null)", target = "field")
    HarvestVM toVM(Harvest harvest);
}
