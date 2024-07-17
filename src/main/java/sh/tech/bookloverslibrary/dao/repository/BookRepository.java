package sh.tech.bookloverslibrary.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sh.tech.bookloverslibrary.dao.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
