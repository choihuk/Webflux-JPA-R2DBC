package playground.couponsystem.domain.coupon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import playground.couponsystem.domain.coupon.dto.request.IssueCouponWalletRequest;
import playground.couponsystem.domain.coupon.dto.response.IssueCouponWalletResponse;
import playground.couponsystem.domain.coupon.service.CouponWalletService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coupon-wallets")
@RequiredArgsConstructor
@Tag(name = "쿠폰 지갑 API", description = "쿠폰이 유저에게 발급되면 쿠폰 지갑")
public class CouponWalletController {

    private final CouponWalletService couponWalletService;

    @Operation(summary = "유저의 쿠폰 지갑에 쿠폰 발급 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "쿠폰 지갑에 쿠폰 발급 성공")
    })
    @PostMapping("/issue")
    public Mono<ResponseEntity<IssueCouponWalletResponse>> issueCoupon
            (@Valid @RequestBody Mono<IssueCouponWalletRequest> request) {
        return request.flatMap(requestDto -> couponWalletService.issueCoupon(requestDto.userId(),
                                                                             requestDto.couponId()))
                      .map(responseDto -> ResponseEntity.status(HttpStatus.CREATED)
                                                     .body(responseDto));
    }
}
