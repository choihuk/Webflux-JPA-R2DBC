package playground.couponsystem.domain.coupon.service;

import playground.couponsystem.domain.coupon.dto.request.CreateCouponRequest;
import playground.couponsystem.domain.coupon.dto.response.CreateCouponResponse;
import reactor.core.publisher.Mono;

public interface CouponService {
    Mono<CreateCouponResponse> createCoupon(CreateCouponRequest dto);
}
