package org.example.service;

import org.example.dto.auth.AuthResponse;
import org.example.dto.auth.SignupRequest;
import org.example.dto.auth.UserDto;
import org.example.model.User;
import org.example.repository.UserRepository;
import org.example.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public AuthResponse signup(SignupRequest req) {
        if (req.getEmail() == null || req.getEmail().isBlank() ||
                req.getPassword() == null || req.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email and password are required");
        }
        if (userRepository.existsByEmail(req.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already in use");
        }
        User user = new User();
        user.setEmail(req.getEmail());
        // Plain text per request (not recommended)
        user.setPassword(req.getPassword());
        user = userRepository.save(user);

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, new UserDto(user.getId(), user.getEmail()));
    }

    public AuthResponse login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));

        // Plain text compare
        if (!user.getPassword().equals(password)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return new AuthResponse(token, new UserDto(user.getId(), user.getEmail()));
    }
}