package com.example.LMS.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface SMSRepository<T, ID> extends JpaRepository<T, ID> {

}

