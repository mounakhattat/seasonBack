package com.season.controllers;
import com.season.entities.User;
import com.season.jwt.JwtService;
import com.season.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    UserService userService;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody User user) {
        User savedUser = userService.signup(user);
        String token = jwtService.generateToken(user.getUsername(),user.getId(), user.getRole());
        Map<String, Object> response = new HashMap<>();
        response.put("id", savedUser.getId());
        response.put("username", savedUser.getUsername());
        response.put("email", savedUser.getEmail());
        response.put("token", token);
        return ResponseEntity.ok(response);
    }


    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> signin(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        User authenticatedUser = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user.getUsername(),user.getId(), user.getRole());
        Map<String, Object> response = new HashMap<>();
        response.put("id", authenticatedUser.getId());
        response.put("username", authenticatedUser.getUsername());
        response.put("email", authenticatedUser.getEmail());
        response.put("token", token);
        return ResponseEntity.ok(response);
    }
}
