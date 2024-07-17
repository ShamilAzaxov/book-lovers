package sh.tech.bookloverslibrary.mapper.rent;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import sh.tech.bookloverslibrary.dao.entity.RentBook;
import sh.tech.bookloverslibrary.model.dto.rent.RentRequestDto;
import sh.tech.bookloverslibrary.model.dto.rent.RentResponseDto;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RentMapper {
//    @Mapping(source = "bookId", target = "book.id")
//    @Mapping(source = "userId", target = "user.id")
    RentBook toEntity(RentRequestDto requestDto);
//    @Mapping(source = "book.id", target = "bookId")
//    @Mapping(source = "user.id", target = "userId")
    RentResponseDto toDto(RentBook rentBook);
}