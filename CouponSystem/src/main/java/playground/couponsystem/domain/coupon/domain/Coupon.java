package playground.couponsystem.domain.coupon.domain;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "coupon")
@Getter
@NoArgsConstructor(access = lombok.AccessLevel.PROTECTED)
public class Coupon {

    @Id
    private Long id;

    private String code;

    private CouponType type;

    private Double discount;

    private Long amount;

    @CreatedDate
    private LocalDateTime createdAt;

    @Builder
    public Coupon(String code, CouponType type, Double discount, Long amount) {
        this.code = code;
        this.type = type;
        this.discount = discount;
        this.amount = amount;
    }
}
