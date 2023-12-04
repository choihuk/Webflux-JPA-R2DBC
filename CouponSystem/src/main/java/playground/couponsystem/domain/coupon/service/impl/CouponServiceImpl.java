package playground.couponsystem.domain.coupon.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import playground.couponsystem.domain.coupon.domain.Coupon;
import playground.couponsystem.domain.coupon.dto.request.CreateCouponRequest;
import playground.couponsystem.domain.coupon.dto.response.CreateCouponResponse;
import playground.couponsystem.domain.coupon.repository.CouponRepository;
import playground.couponsystem.domain.coupon.service.CouponService;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    @Transactional
    public Mono<CreateCouponResponse> createCoupon(CreateCouponRequest dto) {
        return couponRepository.save(new Coupon(dto.code(), dto.type(), dto.discount()))
                               .map(coupon -> CreateCouponResponse.of(coupon.getId()));
    }
}
