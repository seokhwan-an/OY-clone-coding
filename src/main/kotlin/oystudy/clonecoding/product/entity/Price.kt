package oystudy.clonecoding.product.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.math.BigDecimal

private val MAX_PRICE = BigDecimal.valueOf(500_000)

@Embeddable
data class Price(

    @Column(nullable = false, name = "price")
    val value: BigDecimal

) {
    init {
        require(value.compareTo(BigDecimal.ZERO) > 0) {"상품 가격은 0원보다 커야 합니다."}
        require(value.compareTo(MAX_PRICE) <= 0) {"상품 가격은 500,000원보다 이하여야 합니다."}
    }
}
