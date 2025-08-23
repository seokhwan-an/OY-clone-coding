package oystudy.clonecoding.product.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.math.BigDecimal

class PriceTest {

    @DisplayName("생성 실패 - 상품 가격0 이하의 값")
    @Test
    fun fail_create_price_with_zero() {
        val value = BigDecimal.ZERO
        Assertions.assertThatThrownBy{Price(value)}
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("상품 가격은 0원보다 커야 합니다.")
    }

    @DisplayName("생성 실패 - 상품 가격500,000 초과의 값")
    @Test
    fun fail_create_price_with_max() {
        val value = BigDecimal.valueOf(500_001)
        Assertions.assertThatThrownBy{Price(value)}
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("상품 가격은 500,000원보다 이하여야 합니다.")
    }
}
