package com.example.webfluxjpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webfluxjpa.dto.CreateUserRequest;
import com.example.webfluxjpa.dto.UserResponse;
import com.example.webfluxjpa.service.UserService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public Mono<UserResponse> findById(@PathVariable(name = "id") Long userId) {
        return userService.findById(userId);
    }

    @PostMapping
    public Mono<Void> createUser(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }
}
