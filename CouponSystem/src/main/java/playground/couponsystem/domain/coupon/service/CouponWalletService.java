package playground.couponsystem.domain.coupon.service;

import reactor.core.publisher.Mono;

public interface CouponWalletService {
    Mono<?> issueCoupon(Long userId, Long couponCode);
}
