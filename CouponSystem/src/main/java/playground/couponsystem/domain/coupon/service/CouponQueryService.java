package playground.couponsystem.domain.coupon.service;

import playground.couponsystem.domain.coupon.domain.Coupon;
import reactor.core.publisher.Mono;

public interface CouponQueryService {
    Mono<Coupon> getCoupon(Long couponId);
}
