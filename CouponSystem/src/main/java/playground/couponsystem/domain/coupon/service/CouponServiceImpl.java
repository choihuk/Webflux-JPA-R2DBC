package playground.couponsystem.domain.coupon.service;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.ReactiveStringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import playground.couponsystem.domain.coupon.repository.CouponRepository;
import playground.couponsystem.domain.user.repository.UserRepository;
import reactor.core.publisher.Mono;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{

    private final UserRepository userRepository;
    private final CouponRepository couponRepository;
    private final ReactiveStringRedisTemplate redisTemplate;

    @Override
    public Mono<Void> issueCoupon(Long userId, String couponCode) {
        return userRepository.findById(userId)
                .flatMap(user -> {
                    if (user == null) {
                        return Mono.error(new RuntimeException("User not found"));
                    }

                    ByteBuffer scriptByteBuffer = StandardCharsets.UTF_8.encode("local key = KEYS[1]\n" +
                                                                                "local userId = ARGV[1]\n" +
                                                                                "local remainingQuantity = redis.call('SCARD', key)\n" +
                                                                                "if remainingQuantity < 1000 then\n" +
                                                                                "    return redis.call('SADD', key, userId) > 0\n" +
                                                                                "else\n" +
                                                                                "    return false\n" +
                                                                                "end");
                    ByteBuffer keyByteBuffer = StandardCharsets.UTF_8.encode("coupon:" + couponCode);
                    ByteBuffer userIdByteBuffer = StandardCharsets.UTF_8.encode(userId.toString());
                    return redisTemplate.execute(connection ->
                            connection.scriptingCommands().eval(
                                    scriptByteBuffer,
                                    ReturnType.BOOLEAN,
                                    1,
                                    keyByteBuffer,
                                    userIdByteBuffer
                            )).flatMap(result -> {
                                if ((boolean) result) {
                                    return Mono.empty();
                                } else {
                                    return Mono.error(new RuntimeException("Coupon is out of stock"));
                                }
                            }).then();
                });
    }
}
