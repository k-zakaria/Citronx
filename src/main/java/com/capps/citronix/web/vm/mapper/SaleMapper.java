package com.capps.citronix.web.vm.mapper;

import com.capps.citronix.domain.Sale;
import com.capps.citronix.web.vm.request.SaleRequestVM;
import com.capps.citronix.web.vm.sale.SaleVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {
    @Mapping(source = "harvest.id", target = "harvestId")
    SaleVM toVM(Sale sale);

    @Mapping(source = "harvestId", target = "harvest.id")
    Sale toEntity(SaleRequestVM saleRequestVM);
}
