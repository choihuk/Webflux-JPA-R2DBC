package playground.couponsystem.domain.coupon.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import playground.couponsystem.domain.coupon.domain.Coupon;

public interface CouponRepository extends R2dbcRepository<Coupon, Long> {

}
