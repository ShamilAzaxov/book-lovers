package sh.tech.bookloverslibrary.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import sh.tech.bookloverslibrary.filter.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorization -> authorization
                        .requestMatchers("/api/user/auth/**", "/api/admin/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/user", "/api/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/book", "/api/book/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/book").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/book/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/book/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/api/rents", "/api/rents/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/rents").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/rents/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/rents/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .maximumSessions(1))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }
}
