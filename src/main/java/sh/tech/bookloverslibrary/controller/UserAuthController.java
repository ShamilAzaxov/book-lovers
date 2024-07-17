package sh.tech.bookloverslibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sh.tech.bookloverslibrary.model.dto.jwt.JwtAuthenticationRequest;
import sh.tech.bookloverslibrary.model.dto.jwt.JwtAuthenticationResponse;
import sh.tech.bookloverslibrary.model.dto.user.UserRequestDto;
import sh.tech.bookloverslibrary.service.auth.user.UserAuthenticationService;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user/auth")
public class UserAuthController {
    private final UserAuthenticationService authService;

    @PostMapping("/register")
    @ResponseStatus(CREATED)
    public ResponseEntity<JwtAuthenticationResponse> register(@RequestBody UserRequestDto request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody JwtAuthenticationRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}