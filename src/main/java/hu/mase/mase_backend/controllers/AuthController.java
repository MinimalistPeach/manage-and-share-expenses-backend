package hu.mase.mase_backend.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.mase.mase_backend.models.entity.User;
import hu.mase.mase_backend.repositories.UserRepository;
import hu.mase.mase_backend.utils.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder encoder;
    private JwtUtil jwtUtils;

    public AuthController(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            PasswordEncoder encoder,
            JwtUtil jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public String loginByEmail(@RequestBody User user) {
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser == null) {
            return "User not found!";
        }
        
        if (!encoder.matches(user.getPassword(), existingUser.getPassword())) {
            return "Invalid password!";
        }
        
        return jwtUtils.generateToken(existingUser.getEmail());
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return "User already exists!";
        }

        final User newUser = new User(
                null,
                user.getUsername(),
                user.getEmail(),
                encoder.encode(user.getPassword()));
        userRepository.save(newUser);
        return "User registered successfully!";
    }
}