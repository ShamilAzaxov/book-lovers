package sh.tech.bookloverslibrary.service.auth.admin;

import sh.tech.bookloverslibrary.model.dto.jwt.JwtAuthenticationRequest;
import sh.tech.bookloverslibrary.model.dto.jwt.JwtAuthenticationResponse;

public interface AdminAuthenticationService {
    JwtAuthenticationResponse login(JwtAuthenticationRequest request);
}
