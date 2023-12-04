package playground.couponsystem.domain.coupon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import playground.couponsystem.domain.coupon.dto.request.IssueCouponRequest;
import playground.couponsystem.domain.coupon.service.CouponWalletService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coupon-wallets")
@RequiredArgsConstructor
public class CouponWalletController {

    private final CouponWalletService couponWalletService;

    @PostMapping("/issue")
    public Mono<ResponseEntity<?>> issueCoupon(@Valid @RequestBody Mono<IssueCouponRequest> request) {
        return request.flatMap(requestDto ->
                                       couponWalletService.issueCoupon(requestDto.userId(),
                                                                       requestDto.couponId()))
                      .map(ResponseEntity::ok);
    }
}
