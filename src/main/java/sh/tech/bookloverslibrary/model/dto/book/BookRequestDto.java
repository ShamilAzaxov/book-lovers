package sh.tech.bookloverslibrary.model.dto.book;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class BookRequestDto {
    String name;
    String writer;
    String publisher;
    LocalDate publishYear;
    String description;
}
