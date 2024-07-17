package sh.tech.bookloverslibrary.service.auth.user;


import sh.tech.bookloverslibrary.model.dto.jwt.JwtAuthenticationRequest;
import sh.tech.bookloverslibrary.model.dto.jwt.JwtAuthenticationResponse;
import sh.tech.bookloverslibrary.model.dto.user.UserRequestDto;

public interface UserAuthenticationService {
    JwtAuthenticationResponse register(UserRequestDto request);

    JwtAuthenticationResponse login(JwtAuthenticationRequest request);

}
