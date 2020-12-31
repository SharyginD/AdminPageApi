package my.project.util.mapStruct;

import my.project.domain.dto.User;
import my.project.domain.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toDTO(UserEntity userEntity);

    UserEntity toEntity(User user);
}
