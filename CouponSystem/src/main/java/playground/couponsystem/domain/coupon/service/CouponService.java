package playground.couponsystem.domain.coupon.service;

import reactor.core.publisher.Mono;

public interface CouponService {
    Mono<Void> issueCoupon(Long userId, String couponCode);
}
