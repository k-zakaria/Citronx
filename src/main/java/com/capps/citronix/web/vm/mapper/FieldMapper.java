package com.capps.citronix.web.vm.mapper;

import com.capps.citronix.domain.Field;
import com.capps.citronix.service.dto.field.FieldDTO;
import com.capps.citronix.web.vm.field.FieldVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FieldMapper {
    @Mapping(target = "trees", ignore = true) // Ignorer les arbres au moment du mapping
    Field toFieldEntity(FieldDTO fieldDTO);

    @Mapping(source = "farm.name", target = "farmName")
    @Mapping(expression = "java(field.getTrees().size())", target = "numberOfTrees")
    @Mapping(expression = "java(field.getHarvest() != null)", target = "hasHarvest")
    FieldVM toVM(Field field);
}
