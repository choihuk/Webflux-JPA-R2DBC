package playground.couponsystem.domain.coupon.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;

import playground.couponsystem.domain.coupon.domain.CouponWallet;

public interface CouponWalletRepository extends R2dbcRepository<CouponWallet, Long> {

}
