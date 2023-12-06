package playground.couponsystem.domain.coupon.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import playground.couponsystem.domain.coupon.dto.request.IssueCouponRequest;
import playground.couponsystem.domain.coupon.dto.response.CouponInfoResponse;
import playground.couponsystem.domain.coupon.dto.response.CreateCouponResponse;
import playground.couponsystem.domain.coupon.service.CouponService;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
@Tag(name = "쿠폰 API")
public class CouponController {

    private final CouponService couponService;

    @Operation(summary = "쿠폰 발급 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "쿠폰 생성 성공")
    })
    @PostMapping
    public Mono<ResponseEntity<CreateCouponResponse>> issueCoupon(
            @Valid @RequestBody Mono<IssueCouponRequest> request) {
        return request.flatMap(couponService::createCoupon)
                        .map(responseDto -> ResponseEntity.status(HttpStatus.CREATED)
                                                       .body(responseDto));
    }

    @Operation(summary = "쿠폰 발급 목록 조회 API")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "쿠폰 발급 목록 반환 성공")
    })
    @GetMapping
    public Mono<ResponseEntity<Page<CouponInfoResponse>>> getCouponsInfo(
            @Parameter(name = "page", description = "페이지네이션의 페이지 넘버. 0부터 시작함", in = ParameterIn.QUERY)
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @Parameter(name = "size", description = "페이지네이션의 페이지당 데이터 수", in = ParameterIn.QUERY)
            @RequestParam(value = "size", required = false, defaultValue = "20") int size) {
        return Mono.just(PageRequest.of(page, size))
                   .flatMap(couponService::getCouponsInfo)
                       .map(ResponseEntity::ok);
    }
}
