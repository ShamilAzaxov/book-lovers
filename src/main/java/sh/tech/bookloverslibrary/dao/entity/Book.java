package sh.tech.bookloverslibrary.dao.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "book_name", nullable = false)
    String name;
    String writer;
    @Column(nullable = false)
    String publisher;
    @Column(name = "publish_year",nullable = false)
    LocalDate publishYear;
    @ColumnDefault("false")
    boolean isRented;
    String description;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
     User user;
    @OneToMany(mappedBy = "book",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<RentBook> rents;
}