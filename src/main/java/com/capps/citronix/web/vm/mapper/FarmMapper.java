package com.capps.citronix.web.vm.mapper;

import com.capps.citronix.domain.Farm;
import com.capps.citronix.service.dto.FarmSearchCriteria;
import com.capps.citronix.web.vm.farm.FarmVM;
import com.capps.citronix.web.vm.request.FarmRequestVM;
import com.capps.citronix.web.vm.request.FarmSearchCriteriaRequestVM;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface FarmMapper {

    Farm toEntity(FarmRequestVM farmRequestVM);
    FarmVM toVM(Farm farm);

    FarmSearchCriteria toDTO(FarmSearchCriteriaRequestVM farmSearchCriteriaVM);
}