package sh.tech.bookloverslibrary.model.dto.jwt;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtAuthenticationRequest { //LoginRequest

    String email;

    String password;
}
