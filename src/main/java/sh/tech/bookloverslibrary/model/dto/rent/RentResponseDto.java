package sh.tech.bookloverslibrary.model.dto.rent;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RentResponseDto {
    Long id;
    LocalDateTime beginDate;
    LocalDateTime endDate;
    BigDecimal fine;
    Long bookId;
    Long userId;
}
