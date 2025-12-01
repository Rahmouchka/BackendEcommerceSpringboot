package com.iset.BackendEcommerce.controller.auth;
import com.iset.BackendEcommerce.dto.request.LoginRequest;
import com.iset.BackendEcommerce.dto.request.RegisterRequest;
import com.iset.BackendEcommerce.dto.response.AuthResponse;
import com.iset.BackendEcommerce.entities.Role;
import com.iset.BackendEcommerce.entities.User;
import com.iset.BackendEcommerce.dao.UserRepository;
import com.iset.BackendEcommerce.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager; // ON LE GARDE, Spring le crée maintenant

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            throw new RuntimeException("Email déjà utilisé");
        }

        User user = new User();
        user.setNom(req.nom());
        user.setPrenom(req.prenom());
        user.setEmail(req.email());
        user.setPassword(passwordEncoder.encode(req.password()));
        user.setTelephone(req.telephone());
        user.setAdresse(req.adresse());
        user.setRole(Role.USER);
        user.setEnabled(true);

        userRepository.save(user);

        String token = jwtService.generateToken(
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .authorities("ROLE_" + user.getRole().name())
                        .build()
        );

        return ResponseEntity.ok(new AuthResponse(token, user.getRole().name(), user.getEmail()));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password())  // ← email comme username
        );

        User user = userRepository.findByEmail(req.email())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        String token = jwtService.generateToken((UserDetails) authentication.getPrincipal());

        return ResponseEntity.ok(new AuthResponse(token, user.getRole().name(), user.getEmail()));
    }
}