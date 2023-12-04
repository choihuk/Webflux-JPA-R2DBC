package playground.couponsystem.domain.coupon.dto.response;

public record IssueCouponResponse(Long couponWalletId) {

    public static IssueCouponResponse of(Long couponWalletId) {
        return new IssueCouponResponse(couponWalletId);
    }
}
