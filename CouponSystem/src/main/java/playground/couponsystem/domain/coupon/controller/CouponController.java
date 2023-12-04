package playground.couponsystem.domain.coupon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import playground.couponsystem.domain.coupon.dto.request.CreateCouponRequest;
import playground.couponsystem.domain.coupon.dto.response.CreateCouponResponse;
import playground.couponsystem.domain.coupon.service.CouponService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/create")
    public Mono<ResponseEntity<CreateCouponResponse>> createCoupon(
            @Valid @RequestBody Mono<CreateCouponRequest> request) {
        return request.flatMap(couponService::createCoupon)
                        .map(ResponseEntity::ok);
    }
}
