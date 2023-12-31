package playground.couponsystem.domain.coupon.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import playground.couponsystem.domain.coupon.domain.Coupon;

@Builder(access = lombok.AccessLevel.PRIVATE)
public record CouponInfoResponse(@Schema(description = "쿠폰 id") Long couponId,
                                 @Schema(description = "쿠폰 이름") String name,
                                 @Schema(description = "쿠폰 코드") String code,
                                 @Schema(description = "쿠폰 종류") String type,
                                 @Schema(description = "할인 정도") Double discount,
                                 @Schema(description = "쿠폰 발급 양", minimum = "1") Long amount,
                                 @Schema(description = "쿠폰 생성일")
                                 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
                                 LocalDateTime createdAt) {

    public static CouponInfoResponse of(Coupon coupon) {
        return CouponInfoResponse.builder()
                                 .couponId(coupon.getId())
                                 .name(coupon.getName())
                                 .code(coupon.getCode())
                                 .type(coupon.getType().name())
                                 .discount(coupon.getDiscount())
                                 .amount(coupon.getAmount())
                                 .createdAt(coupon.getCreatedAt())
                                 .build();
    }
}
