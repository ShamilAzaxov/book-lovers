package sh.tech.bookloverslibrary.service.auth.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sh.tech.bookloverslibrary.dao.repository.UserRepository;
import sh.tech.bookloverslibrary.mapper.user.UserMapper;
import sh.tech.bookloverslibrary.model.dto.jwt.JwtAuthenticationRequest;
import sh.tech.bookloverslibrary.model.dto.jwt.JwtAuthenticationResponse;
import sh.tech.bookloverslibrary.model.dto.user.UserRequestDto;
import sh.tech.bookloverslibrary.service.jwt.JwtService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    UserMapper userMapper;

    UserRepository userRepository;

    PasswordEncoder encoder;

    JwtService jwtService;

    AuthenticationManager authManager;

    @Override
    public JwtAuthenticationResponse register(UserRequestDto request) {
        //Register the user to repository and generate a token
        var user = userMapper.toEntity(request);
        user.setPassword(encoder.encode(request.getPassword()));
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwtToken);
    }

    @Override
    public JwtAuthenticationResponse login(JwtAuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return new JwtAuthenticationResponse(jwtToken);
    }
}
