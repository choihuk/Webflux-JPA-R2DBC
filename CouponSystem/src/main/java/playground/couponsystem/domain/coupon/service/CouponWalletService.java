package playground.couponsystem.domain.coupon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import playground.couponsystem.domain.coupon.dto.response.CouponWalletResponse;
import playground.couponsystem.domain.coupon.dto.response.IssueCouponWalletResponse;
import reactor.core.publisher.Mono;

public interface CouponWalletService {
    Mono<IssueCouponWalletResponse> issueCoupon(Long userId, Long couponId);

    Mono<Page<CouponWalletResponse>> getCouponWallets(Pageable pageable);
}
