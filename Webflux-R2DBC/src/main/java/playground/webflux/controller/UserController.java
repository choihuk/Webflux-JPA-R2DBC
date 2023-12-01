package playground.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import playground.webflux.dto.CreateUserRequest;
import playground.webflux.dto.UserResponse;
import playground.webflux.service.UserService;
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
