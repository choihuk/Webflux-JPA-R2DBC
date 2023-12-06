package playground.couponsystem.domain.coupon.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import playground.couponsystem.domain.coupon.domain.Coupon;
import playground.couponsystem.domain.coupon.dto.request.IssueCouponRequest;
import playground.couponsystem.domain.coupon.dto.response.CouponInfoResponse;
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
    public Mono<CreateCouponResponse> createCoupon(final IssueCouponRequest dto) {
        return couponRepository.save(Coupon.builder()
                                           .name(dto.name())
                                           .code(dto.code())
                                           .type(dto.type())
                                           .discount(dto.discount())
                                           .amount(dto.amount())
                                           .build())
                               .map(coupon -> CreateCouponResponse.of(coupon.getId()));
    }

    @Override
    public Mono<Page<CouponInfoResponse>> getCouponsInfo(final Pageable pageable) {
        return couponRepository.findAllBy(pageable)
                .map(CouponInfoResponse::of)
                .collectList()
                .zipWith(couponRepository.count())
                .map(p -> new PageImpl<>(p.getT1(), pageable, p.getT2()));
    }
}
