package com.example.webfluxjpa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.webfluxjpa.domain.User;
import com.example.webfluxjpa.dto.CreateUserRequest;
import com.example.webfluxjpa.dto.UserResponse;
import com.example.webfluxjpa.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<UserResponse> findById(final Long userId) {
        return Mono.defer(() -> userRepository.findById(userId)
                                              .map(user -> new UserResponse(user.getUsername()))
                                              .map(Mono::just)
                                              .orElse(Mono.error(new RuntimeException("User not found"))));
    }



    @Transactional
    public Mono<Void> createUser(final CreateUserRequest request) {
        return Mono.fromRunnable(() -> userRepository.save(new User(request.username())))
                   .then();
    }


}
