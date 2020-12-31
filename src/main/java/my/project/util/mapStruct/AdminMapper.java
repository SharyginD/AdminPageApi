package my.project.util.mapStruct;

import my.project.domain.dto.Admin;
import my.project.domain.entity.AdminEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AdminMapper {

    AdminMapper INSTANCE = Mappers.getMapper(AdminMapper.class);

    Admin toDTO(AdminEntity entity);

    AdminEntity toEntity(Admin admin);
}
