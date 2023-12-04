package playground.couponsystem.domain.coupon.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import playground.couponsystem.domain.coupon.domain.CouponType;

public record CreateCouponRequest(@NotBlank String code,
                                  @NotNull CouponType type,
                                  @NotNull @Positive Double discount,
                                  @NotNull @Positive Double amount) {
}
