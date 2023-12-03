package playground.couponsystem.domain.coupon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import playground.couponsystem.domain.coupon.dto.request.CouponIssueRequest;
import playground.couponsystem.domain.coupon.service.CouponService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping("/issue")
    public Mono<ResponseEntity<?>> issueCoupon(@RequestBody Mono<CouponIssueRequest> request) {
        return request.flatMap(dto ->
                               couponService.issueCoupon(dto.userId(), dto.couponCode()))
                      .then(Mono.just(ResponseEntity.ok().build()));
    }
}
