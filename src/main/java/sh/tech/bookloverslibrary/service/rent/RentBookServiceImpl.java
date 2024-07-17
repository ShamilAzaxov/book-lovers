package sh.tech.bookloverslibrary.service.rent;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sh.tech.bookloverslibrary.dao.entity.Book;
import sh.tech.bookloverslibrary.dao.entity.RentBook;
import sh.tech.bookloverslibrary.dao.entity.User;
import sh.tech.bookloverslibrary.dao.repository.BookRepository;
import sh.tech.bookloverslibrary.dao.repository.RentBookRepo;
import sh.tech.bookloverslibrary.dao.repository.UserRepository;
import sh.tech.bookloverslibrary.enums.Status;
import sh.tech.bookloverslibrary.exception.BookNotFoundException;
import sh.tech.bookloverslibrary.exception.RentNotFoundException;
import sh.tech.bookloverslibrary.exception.UserNotFoundException;
import sh.tech.bookloverslibrary.mapper.rent.RentMapper;
import sh.tech.bookloverslibrary.model.dto.rent.RentRequestDto;
import sh.tech.bookloverslibrary.model.dto.rent.RentResponseDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RentBookServiceImpl implements RentBookService {
    private final RentBookRepo rentRepo;
    private final RentMapper rentMapper;
    private final BookRepository bookRepository;
    private final UserRepository userRepo;

    @Override
    public List<RentResponseDto> getAllRents() {
        return rentRepo.findAll().stream().map(rentMapper::toDto).toList();
    }

    @Override
    public RentResponseDto getRentById(Long id) {
        return rentRepo.findById(id).map(rentMapper::toDto).orElseThrow(() ->
                new RentNotFoundException("Rent Not Found by id: " + id));
    }

    @Transactional
    @Override
    public void saveRent(RentRequestDto requestDto) {
        RentBook rentBook = rentMapper.toEntity(requestDto);
        Book book = bookRepository.findById(requestDto.getBookId()).orElseThrow(() ->
                new BookNotFoundException("Book Not Found by id: " + requestDto.getBookId()));
        User user = userRepo.findById(requestDto.getUserId()).orElseThrow(() ->
                new UserNotFoundException("User Not Found by id: " + requestDto.getUserId()));
        rentBook.setBook(book);
        rentBook.setUser(user);
        if (!book.isRented()) {
            book.setRented(true);
            book.setUser(user);
            bookRepository.save(book);
            rentBook.setStatus(Status.ACTIVE);
            rentRepo.save(rentBook);
        }
    }

    @Override
    public void updateRent(Long id, RentRequestDto requestDto) {
        RentBook rentBook = rentMapper.toEntity(requestDto);
        rentBook.setId(id);
        rentRepo.save(rentBook);
    }

    @Transactional
    @Override
    public void deleteRentById(Long id) {
        RentBook rentBook = rentRepo.findById(id).orElseThrow(() ->
                new RentNotFoundException("Rent Not Found by id: " + id));
        if (rentBook.getStatus().equals(Status.ACTIVE)) {
            rentBook.setStatus(Status.DELETED);
            rentRepo.save(rentBook);
        }
        Book book = bookRepository.findById(rentBook.getBook().getId()).orElseThrow(() ->
                new BookNotFoundException("Book Not Found by id: " + rentBook.getBook().getId()));
        if (book.isRented()) {
            book.setRented(false);
            book.setUser(null);
            bookRepository.save(book);
        }
    }

    @Override
    public List<RentResponseDto> getActiveRents() {
        return rentRepo.findRentBooksByActiveStatus().stream().map(rentMapper::toDto).toList();
    }
}
