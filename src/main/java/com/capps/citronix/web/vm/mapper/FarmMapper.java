package com.capps.citronix.web.vm.mapper;

import com.capps.citronix.domain.Farm;
import com.capps.citronix.service.dto.farm.FarmDTO;
import com.capps.citronix.web.vm.farm.FarmVM;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface FarmMapper {
    Farm toFarmEntity(FarmDTO farmDTO);
    FarmVM toVM(Farm farm);
}