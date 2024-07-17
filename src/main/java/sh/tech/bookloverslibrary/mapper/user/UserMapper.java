package sh.tech.bookloverslibrary.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import sh.tech.bookloverslibrary.dao.entity.User;
import sh.tech.bookloverslibrary.model.dto.user.UserRequestDto;
import sh.tech.bookloverslibrary.model.dto.user.UserResponseDto;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    @Mapping(target = "role", constant = "USER")
    User toEntity(UserRequestDto userRequestDto);

    UserResponseDto toDto(User user);
}