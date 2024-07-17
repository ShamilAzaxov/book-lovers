package sh.tech.bookloverslibrary.service.book;



import sh.tech.bookloverslibrary.model.dto.book.BookRequestDto;
import sh.tech.bookloverslibrary.model.dto.book.BookResponseDto;

import java.util.List;

public interface BookService {
    List<BookResponseDto> getAllBook();
    BookResponseDto getBookById(Long id);
    void saveBook(BookRequestDto bookDto);
    void updateBook(Long id, BookRequestDto bookDto);
    void deleteBook(Long id);

}
