package playground.couponsystem.domain.coupon.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import playground.couponsystem.domain.coupon.dto.request.CreateCouponRequest;
import playground.couponsystem.domain.coupon.dto.response.CouponInfoResponse;
import playground.couponsystem.domain.coupon.dto.response.CreateCouponResponse;
import reactor.core.publisher.Mono;

public interface CouponService {
    Mono<CreateCouponResponse> createCoupon(CreateCouponRequest dto);

    Mono<Page<CouponInfoResponse>> getCoupons(Pageable pageable);
}
