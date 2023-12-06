package playground.couponsystem.domain.coupon.exception;

import playground.couponsystem.common.exception.BusinessException;
import playground.couponsystem.common.exception.ErrorCode;

public class CouponNotFoundException extends BusinessException {
    public CouponNotFoundException() {
        super(ErrorCode.COUPON_NOT_FOUND);
    }
}
