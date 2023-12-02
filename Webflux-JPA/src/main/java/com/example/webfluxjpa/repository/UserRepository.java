package com.example.webfluxjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.webfluxjpa.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
