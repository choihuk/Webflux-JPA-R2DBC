package playground.couponsystem.domain.user.exception;

import playground.couponsystem.common.exception.BusinessException;
import playground.couponsystem.common.exception.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
