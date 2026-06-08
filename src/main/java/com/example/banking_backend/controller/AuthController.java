package com.example.banking_backend.controller;

import com.example.banking_backend.model.User;
import com.example.banking_backend.repository.UserRepository;
import com.example.banking_backend.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.get("username"))) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        User user = new User(signUpRequest.get("username"), encoder.encode(signUpRequest.get("password")));
        userRepository.save(user);
        return ResponseEntity.ok("User credential registered successfully via security module!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> loginRequest) {
        User user = userRepository.findByUsername(loginRequest.get("username"))
                .orElseThrow(() -> new RuntimeException("Error: Invalid login user reference"));

        if (!encoder.matches(loginRequest.get("password"), user.getPassword())) {
            return ResponseEntity.status(401).body("Error: Credentials Password Mismatch");
        }

        String jwt = jwtUtils.generateJwtToken(user.getUsername());

        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("type", "Bearer");
        response.put("username", user.getUsername());

        return ResponseEntity.ok(response);
    }
}
