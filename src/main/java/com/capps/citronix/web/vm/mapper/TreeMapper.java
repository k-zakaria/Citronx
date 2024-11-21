package com.capps.citronix.web.vm.mapper;

import com.capps.citronix.domain.Tree;
import com.capps.citronix.service.dto.tree.TreeDTO;
import com.capps.citronix.web.vm.request.TreeRequestVM;
import com.capps.citronix.web.vm.tree.TreeVM;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TreeMapper {
    @Mapping(source = "field.id", target = "fieldId")
    TreeVM toVM(Tree tree);

    TreeDTO toDTO(TreeRequestVM treeRequestVM);

    @Mapping(source = "fieldId", target = "field.id")
    Tree toEntity(TreeDTO treeDTO);
}
