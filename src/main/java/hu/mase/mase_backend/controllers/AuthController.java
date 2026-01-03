package hu.mase.mase_backend.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.mase.mase_backend.models.dto.AuthResponse;
import hu.mase.mase_backend.models.entity.User;
import hu.mase.mase_backend.repositories.UserRepository;
import hu.mase.mase_backend.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private JwtUtil jwtUtils;

    public AuthController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            PasswordEncoder encoder,
            JwtUtil jwtUtils) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginByEmail(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            return ResponseEntity.status(404).body(new AuthResponse("User not found!"));
        }
        
        if (!encoder.matches(user.getPassword(), existingUser.getPassword())) {
            return ResponseEntity.status(401).body(new AuthResponse("Invalid credentials"));
        }

        String token = jwtUtils.generateToken(existingUser.getEmail());
        
        // Set the token in a cookie
        ResponseCookie cookie = ResponseCookie.from("token", token)
                .httpOnly(false)
                .secure(false) // Set to true if using HTTPS
                .sameSite("Lax")
                .path("/")
                .maxAge(12 * 60 * 60) // 12 hours
                .build();
        
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new AuthResponse("Login successful", token));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> registerUser(@RequestBody User user) {
        if (user == null || user.getUsername() == null || user.getUsername().isEmpty() ||
            user.getEmail() == null || user.getEmail().isEmpty() ||
            user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body(new AuthResponse("Missing required fields"));
        }
        if (userRepository.existsByEmail(user.getEmail()) || userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.status(409).body(new AuthResponse("User already exists!"));
        }

        final User newUser = new User(
                null,
                user.getUsername(),
                user.getEmail(),
                encoder.encode(user.getPassword()));
        userRepository.save(newUser);
        return ResponseEntity.ok(new AuthResponse("User registered successfully!"));
    }
}