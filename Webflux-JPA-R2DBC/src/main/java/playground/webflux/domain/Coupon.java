package playground.webflux.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Coupon {

    @Id
    private Long id;
    private Long userId;
    private CouponType type;
    private LocalDateTime used_at;

    public Coupon(CouponType type) {
        this.type = type;
    }

    public void use(Long userId) {
        this.userId = userId;
        this.used_at = LocalDateTime.now();
    }
}
