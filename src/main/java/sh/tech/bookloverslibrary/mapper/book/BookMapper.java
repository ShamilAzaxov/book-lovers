package sh.tech.bookloverslibrary.mapper.book;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import sh.tech.bookloverslibrary.dao.entity.Book;
import sh.tech.bookloverslibrary.model.dto.book.BookRequestDto;
import sh.tech.bookloverslibrary.model.dto.book.BookResponseDto;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BookMapper {
    Book toEntity(BookRequestDto requestDto);

    BookResponseDto toDto(Book book);
}