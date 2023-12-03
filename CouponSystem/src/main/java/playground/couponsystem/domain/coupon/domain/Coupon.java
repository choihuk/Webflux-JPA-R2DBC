package playground.couponsystem.domain.coupon.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "coupon")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Coupon {

    @Id
    private Long id;

    @NotNull
    private String code;

    @NotNull
    private CouponType type;

    @NotNull
    private Double discount;

    @CreatedDate
    private LocalDateTime createdAt;

    private LocalDateTime usedAt;

    private Long userId;

    public Coupon(String code, CouponType type, Double discount) {
        this.code = code;
        this.type = type;
        this.discount = discount;
    }
}
