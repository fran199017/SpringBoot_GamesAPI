package com.example.gamesapi.gamesapi.users.repository;

import com.example.gamesapi.gamesapi.users.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<Users, Integer> {

    @Query("SELECT u FROM Users u WHERE u.email = ?1")
    Users findByEmail(String email);
}