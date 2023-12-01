package playground.webflux.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import playground.webflux.domain.User;
import playground.webflux.dto.CreateUserRequest;
import playground.webflux.dto.UserResponse;
import playground.webflux.repository.UserRepository;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Mono<UserResponse> findById(final Long userId){
        return userRepository.findById(userId)
                .map(user -> new UserResponse(user.getUsername()))
                .doOnNext(userResponse -> log.info("User found: {}", userResponse))
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")));
    }

    @Transactional
    public Mono<Void> createUser(final CreateUserRequest request) {
        return userRepository.save(new User(request.username()))
                .doOnNext(user -> log.info("User created: {}", user))
                .then();
    }

}
