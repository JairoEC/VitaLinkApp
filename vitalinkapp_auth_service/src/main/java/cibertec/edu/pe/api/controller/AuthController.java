package cibertec.edu.pe.api.controller;

import cibertec.edu.pe.api.controller.dto.request.AuthRequest;
import cibertec.edu.pe.api.controller.dto.response.AuthResponse;
import cibertec.edu.pe.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        // Valida las credenciales. Si son incorrectas, lanza una excepción (403 Forbidden)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Si es exitoso, generamos el token
        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user);

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
