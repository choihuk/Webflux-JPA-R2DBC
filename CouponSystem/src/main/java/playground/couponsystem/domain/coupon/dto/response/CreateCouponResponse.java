package playground.couponsystem.domain.coupon.dto.response;

public record CreateCouponResponse(Long couponId) {

    public static CreateCouponResponse of(Long couponId) {
        return new CreateCouponResponse(couponId);
    }
}
