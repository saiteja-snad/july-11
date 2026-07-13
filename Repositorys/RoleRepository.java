package com.example.LMS.Repositorys;

import java.util.Optional;

import com.example.LMS.Entity.Role;

public interface RoleRepository extends SMSRepository<Role, Integer> {

    Optional<Role> findByRoleName(String roleName);
}