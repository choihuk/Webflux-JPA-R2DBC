package playground.couponsystem.domain.coupon.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

import playground.couponsystem.domain.coupon.domain.CouponWallet;
import playground.couponsystem.domain.coupon.dto.response.CouponUsageStatusResponse;
import reactor.core.publisher.Flux;

public interface CouponWalletRepository extends R2dbcRepository<CouponWallet, Long> {


    @Query("SELECT cw.coupon_id, c.name AS coupon_name, cw.user_id, u.username, cw.created_at "
           + "AS coupon_issued_at, cw.used_at AS coupon_used_at "
            + "FROM coupon_wallet cw "
            + "INNER JOIN users u ON cw.user_id = u.id "
            + "INNER JOIN coupon c ON cw.coupon_id = c.id "
            + "ORDER BY cw.created_at DESC "
            + "LIMIT :#{#pageable.pageSize} OFFSET :#{#pageable.offset}")
    Flux<CouponUsageStatusResponse> findAllCouponUsageStatus(Pageable pageable);
}
