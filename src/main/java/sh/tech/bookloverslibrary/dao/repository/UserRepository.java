package sh.tech.bookloverslibrary.dao.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sh.tech.bookloverslibrary.dao.entity.User;

import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
