package playground.couponsystem.domain.coupon.service.impl;

import static playground.couponsystem.common.utils.StringToByteBufferUtils.toByteBuffer;

import java.nio.ByteBuffer;

import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import playground.couponsystem.domain.coupon.domain.Coupon;
import playground.couponsystem.domain.coupon.domain.CouponWallet;
import playground.couponsystem.domain.coupon.dto.response.IssueCouponWalletResponse;
import playground.couponsystem.domain.coupon.repository.CouponWalletRepository;
import playground.couponsystem.domain.coupon.service.CouponQueryService;
import playground.couponsystem.domain.coupon.service.CouponWalletService;
import playground.couponsystem.domain.user.domain.User;
import playground.couponsystem.domain.user.service.UserQueryService;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CouponWalletServiceImpl implements CouponWalletService {

    private final UserQueryService userQueryService;
    private final CouponQueryService couponQueryService;
    private final CouponWalletRepository couponWalletRepository;
    private final ReactiveStringRedisTemplate redisTemplate;

    @Override
    @Transactional
    public Mono<IssueCouponWalletResponse> issueCoupon(final Long userId, final Long couponId) {
        return Mono.zip(userQueryService.getUser(userId), couponQueryService.getCoupon(couponId))
                .flatMap(tuple -> {
                    final User user = tuple.getT1();
                    final Coupon coupon = tuple.getT2();
                    final ByteBuffer scriptByteBuffer = toByteBuffer("local key = KEYS[1]",
                                                                     "local userId = ARGV[1]",
                                                                     "local remainingQuantity = redis.call('SCARD', key)",
                                                                     "if remainingQuantity < " + coupon.getAmount() + " then",
                                                                     "    return redis.call('SADD', key, userId) > 0",
                                                                     "else",
                                                                     "    return false",
                                                                     "end");
                    final ByteBuffer keyByteBuffer = toByteBuffer("coupon:" + coupon.getCode());
                    final ByteBuffer userIdByteBuffer = toByteBuffer(userId.toString());
                    return redisTemplate.execute(connection ->
                            connection.scriptingCommands().eval(
                                    scriptByteBuffer,
                                    ReturnType.BOOLEAN,
                                    1,
                                    keyByteBuffer,
                                    userIdByteBuffer
                            )).flatMap(result -> {
                                if ((boolean) result) {
                                    return couponWalletRepository.save(
                                            new CouponWallet(user.getId(), coupon.getId())
                                    ).flatMap(couponWallet ->
                                                Mono.just(IssueCouponWalletResponse.of(couponWallet.getId())));
                                } else {
                                    return Mono.error(new RuntimeException("Coupon is out of stock"));
                                }
                            }).next();
                });
    }
}
