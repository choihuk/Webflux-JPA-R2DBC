package playground.couponsystem.domain.coupon.dto.request;

import jakarta.validation.constraints.NotNull;

public record IssueCouponRequest(@NotNull Long userId,
                                 @NotNull Long couponId) {
}
