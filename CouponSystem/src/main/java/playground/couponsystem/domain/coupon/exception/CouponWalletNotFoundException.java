package playground.couponsystem.domain.coupon.exception;

import playground.couponsystem.common.exception.BusinessException;
import playground.couponsystem.common.exception.ErrorCode;

public class CouponWalletNotFoundException extends BusinessException {

    public CouponWalletNotFoundException() {
        super(ErrorCode.COUPON_WALLET_NOT_FOUND);
    }
}
