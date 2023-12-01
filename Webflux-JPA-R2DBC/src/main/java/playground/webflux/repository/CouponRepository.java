package playground.webflux.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import playground.webflux.domain.Coupon;

public interface CouponRepository extends R2dbcRepository<Coupon, Long> {
}
