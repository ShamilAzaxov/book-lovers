package sh.tech.bookloverslibrary.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.tech.bookloverslibrary.model.dto.jwt.JwtAuthenticationRequest;
import sh.tech.bookloverslibrary.model.dto.jwt.JwtAuthenticationResponse;
import sh.tech.bookloverslibrary.service.auth.admin.AdminAuthenticationService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/auth")
public class AdminAuthController {
    AdminAuthenticationService authService;
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody JwtAuthenticationRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
