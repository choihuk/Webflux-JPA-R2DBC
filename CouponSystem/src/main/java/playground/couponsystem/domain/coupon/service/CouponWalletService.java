package playground.couponsystem.domain.coupon.service;

import playground.couponsystem.domain.coupon.dto.response.IssueCouponWalletResponse;
import reactor.core.publisher.Mono;

public interface CouponWalletService {
    Mono<IssueCouponWalletResponse> issueCoupon(Long userId, Long couponId);
}
