package com.bankingapplication.repository;

import com.bankingapplication.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
    Users findByEmail(String email);
    Users findByMobileNumber(String mobileNumber);
}