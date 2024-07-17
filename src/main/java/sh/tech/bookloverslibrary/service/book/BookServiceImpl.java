package sh.tech.bookloverslibrary.service.book;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import sh.tech.bookloverslibrary.dao.entity.Book;
import sh.tech.bookloverslibrary.dao.repository.BookRepository;
import sh.tech.bookloverslibrary.exception.BookNotFoundException;
import sh.tech.bookloverslibrary.mapper.book.BookMapper;
import sh.tech.bookloverslibrary.model.dto.book.BookRequestDto;
import sh.tech.bookloverslibrary.model.dto.book.BookResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookServiceImpl implements BookService {
    BookRepository bookRepository;
    BookMapper mapper;

    @Override
    public List<BookResponseDto> getAllBook() {
        return bookRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        return bookRepository.findById(id).map(mapper::toDto).get();
    }

    @Override
    public void saveBook(BookRequestDto requestDto) {
        Book book = mapper.toEntity(requestDto);
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Long id, BookRequestDto requestDto) {
        Book book = mapper.toEntity(requestDto);
        book.setId(id);
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() ->
                new BookNotFoundException("Book Not Found by id " + id));
        bookRepository.delete(book);
    }
}