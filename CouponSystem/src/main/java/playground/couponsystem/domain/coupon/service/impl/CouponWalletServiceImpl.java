package playground.couponsystem.domain.coupon.service.impl;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import playground.couponsystem.domain.coupon.domain.Coupon;
import playground.couponsystem.domain.coupon.domain.CouponWallet;
import playground.couponsystem.domain.coupon.dto.response.IssueCouponResponse;
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
    public Mono<IssueCouponResponse> issueCoupon(final Long userId, final Long couponId) {
        return Mono.zip(userQueryService.getUser(userId), couponQueryService.getCoupon(couponId))
                .flatMap(tuple -> {
                    final User user = tuple.getT1();
                    final Coupon coupon = tuple.getT2();
                    final ByteBuffer scriptByteBuffer = StandardCharsets.UTF_8.encode("local key = KEYS[1]\n" +
                                                                                "local userId = ARGV[1]\n" +
                                                                                "local remainingQuantity = redis.call('SCARD', key)\n" +
                                                                                "if remainingQuantity < 1000 then\n" +
                                                                                "    return redis.call('SADD', key, userId) > 0\n" +
                                                                                "else\n" +
                                                                                "    return false\n" +
                                                                                "end");
                    final ByteBuffer keyByteBuffer = StandardCharsets.UTF_8.encode("coupon:" + coupon.getCode());
                    final ByteBuffer userIdByteBuffer = StandardCharsets.UTF_8.encode(userId.toString());
                    return redisTemplate.execute(connection ->
                            connection.scriptingCommands().eval(
                                    scriptByteBuffer,
                                    ReturnType.BOOLEAN,
                                    1,
                                    keyByteBuffer,
                                    userIdByteBuffer
                            )).flatMap(result -> {
                                if ((boolean) result) {
                                    return couponWalletRepository.save(new CouponWallet(
                                                                         user.getId(), coupon.getId()))
                                            .flatMap(couponWallet ->
                                                        Mono.just(new IssueCouponResponse(couponWallet.getId())));
                                } else {
                                    return Mono.error(new RuntimeException("Coupon is out of stock"));
                                }
                            }).next();
                });
    }
}
