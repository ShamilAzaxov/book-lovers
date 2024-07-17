package sh.tech.bookloverslibrary.service.auth.admin;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import sh.tech.bookloverslibrary.dao.repository.UserRepository;
import sh.tech.bookloverslibrary.model.dto.jwt.JwtAuthenticationRequest;
import sh.tech.bookloverslibrary.model.dto.jwt.JwtAuthenticationResponse;
import sh.tech.bookloverslibrary.service.jwt.JwtService;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminAuthenticationServiceImpl implements AdminAuthenticationService{

    UserRepository userRepository;

    JwtService jwtService;

    AuthenticationManager authManager;
    @Override
    public JwtAuthenticationResponse login(JwtAuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwtToken);
    }
}
