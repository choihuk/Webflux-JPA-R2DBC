package playground.couponsystem.domain.coupon.service;

import playground.couponsystem.domain.coupon.dto.response.IssueCouponResponse;
import reactor.core.publisher.Mono;

public interface CouponWalletService {
    Mono<IssueCouponResponse> issueCoupon(Long userId, Long couponCode);
}
