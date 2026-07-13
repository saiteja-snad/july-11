package com.example.LMS.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LMS.Dtos.LoginRequestDTO;
import com.example.LMS.Dtos.UserResponseDTO;
import com.example.LMS.Entity.User;
import com.example.LMS.Repositorys.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Override
    public UserResponseDTO login(LoginRequestDTO request) {

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Username"));

        // Plain text password check
        if (!user.getPasswordHash().equals(request.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtService.generateToken(user);

        UserResponseDTO response = new UserResponseDTO();

        response.setUserId(user.getUserId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());

        if (user.getRole() != null) {
            response.setRoleName(user.getRole().getRoleName());
        }

        response.setStatus(user.getStatus());
        response.setCreatedAt(user.getCreatedAt());
        response.setToken(token);

        return response;
    }

    
}