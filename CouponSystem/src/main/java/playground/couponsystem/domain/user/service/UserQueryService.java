package playground.couponsystem.domain.user.service;

import playground.couponsystem.domain.user.domain.User;
import reactor.core.publisher.Mono;

public interface UserQueryService {
    Mono<User> getUser(Long userId);
}
