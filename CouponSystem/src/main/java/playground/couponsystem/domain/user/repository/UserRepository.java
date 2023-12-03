package playground.couponsystem.domain.user.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import playground.couponsystem.domain.user.domain.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends R2dbcRepository<User, Long> {
}
