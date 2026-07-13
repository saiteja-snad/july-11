package com.example.LMS.Services;

import com.example.LMS.Entity.User;

public interface JwtService {

    String generateToken(User user);
}