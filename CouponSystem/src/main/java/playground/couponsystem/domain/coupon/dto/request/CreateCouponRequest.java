package playground.couponsystem.domain.coupon.dto.request;

import playground.couponsystem.domain.coupon.domain.CouponType;

public record CreateCouponRequest(String code, CouponType type, Double discount) {
}
