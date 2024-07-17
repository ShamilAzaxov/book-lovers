package sh.tech.bookloverslibrary.dao.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sh.tech.bookloverslibrary.dao.entity.RentBook;
import java.util.List;

public interface RentBookRepo extends JpaRepository<RentBook, Long> {
    @Query("select r from RentBook r where r.status = 'ACTIVE'")
    List<RentBook> findRentBooksByActiveStatus();
}