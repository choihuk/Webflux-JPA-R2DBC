package playground.couponsystem.domain.coupon.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(description = "유저의 쿠폰 지갑에 쿠폰 발급 요청 dto")
public record IssueCouponWalletRequest(@Schema(description = "유저 id") @NotNull Long userId,
                                       @Schema(description = "쿠폰 id") @NotNull Long couponId) {
}
