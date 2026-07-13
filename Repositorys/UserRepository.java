package com.example.LMS.Repositorys;



import java.util.Optional;

import com.example.LMS.Entity.User;

public interface UserRepository extends SMSRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}