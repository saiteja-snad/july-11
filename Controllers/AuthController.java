package com.example.LMS.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.LMS.Dtos.LoginRequestDTO;
import com.example.LMS.Dtos.UserResponseDTO;
import com.example.LMS.Services.AuthService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    // No @PreAuthorize — login must remain public.
    // Make sure "/api/auth/**" is listed under permitAll() in SecurityConfig.
    @Operation(summary = "User Login")
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(
            @RequestBody LoginRequestDTO request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
