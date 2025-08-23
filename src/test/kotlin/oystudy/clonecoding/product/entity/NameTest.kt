package oystudy.clonecoding.product.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NameTest {

    @DisplayName("생성 실패 - 상품 이름 공백 혹은 빈칸")
    @ParameterizedTest
    @ValueSource(strings = ["", "   "])
    fun fail_create_name_with_empty_blank(value: String) {
        Assertions.assertThatThrownBy{Name(value)}
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("상품명은 비어 있을 수 없습니다.")
    }

    @DisplayName("생성 실패 - 상품 이름 길이 초과")
    @Test
    fun fail_create_name_with_length_exceed() {
        val value = "아".repeat(51)
        Assertions.assertThatThrownBy{Name(value)}
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("상품명은 50자 이하여야 합니다.")
    }
}
