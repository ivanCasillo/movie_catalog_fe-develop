package it.step.mapper;

import it.step.dto.RoleDTO;
import it.step.entity.Role;
import org.mapstruct.Mapper;

@Mapper
public interface RoleMapper {

    Role roleDtoToRole(RoleDTO roleDto);
    RoleDTO roleToRoleDto(Role role);

}
