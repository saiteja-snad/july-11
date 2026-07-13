package com.example.LMS.Services;

import com.example.LMS.Dtos.LoginRequestDTO;
import com.example.LMS.Dtos.UserResponseDTO;

public interface AuthService {

    UserResponseDTO login(LoginRequestDTO request);
}