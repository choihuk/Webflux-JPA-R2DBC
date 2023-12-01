package playground.webflux.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import playground.webflux.domain.User;

public interface UserRepository extends R2dbcRepository<User, Long> {

}
