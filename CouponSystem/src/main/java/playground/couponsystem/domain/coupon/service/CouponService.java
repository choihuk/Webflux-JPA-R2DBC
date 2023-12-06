package playground.couponsystem.domain.coupon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import playground.couponsystem.domain.coupon.dto.request.IssueCouponRequest;
import playground.couponsystem.domain.coupon.dto.response.CouponInfoResponse;
import playground.couponsystem.domain.coupon.dto.response.CreateCouponResponse;
import reactor.core.publisher.Mono;

public interface CouponService {
    Mono<CreateCouponResponse> createCoupon(IssueCouponRequest dto);

    Mono<Page<CouponInfoResponse>> getCouponsInfo(Pageable pageable);

    Mono<CouponInfoResponse> getCouponInfo(Long couponId);
}
