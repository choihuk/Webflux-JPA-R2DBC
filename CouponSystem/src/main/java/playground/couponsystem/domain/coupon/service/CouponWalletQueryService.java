package playground.couponsystem.domain.coupon.service;

import playground.couponsystem.domain.coupon.domain.CouponWallet;
import reactor.core.publisher.Mono;

public interface CouponWalletQueryService {
    Mono<CouponWallet> getCouponWallet(Long couponWalletId);
}
