package com.capps.citronix.web.vm.mapper;

import com.capps.citronix.domain.Harvest;
import com.capps.citronix.service.dto.harvest.HarvestDTO;
import com.capps.citronix.web.vm.harvest.HarvestVM;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HarvestMapper {
    Harvest toEntity(HarvestDTO harvestDTO);

    HarvestVM toVM(Harvest harvest);
}
