package oystudy.clonecoding.product.entity

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

class CategoryTest {

    @DisplayName("올바른 카테고리를 value로 찾는다.")
    @ParameterizedTest
    @MethodSource("valueAndCategory")
    fun find_by_value(input: String, expected: Category) {
        val actual = Category.findByValue(input)

        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun valueAndCategory() = arrayOf(
            arrayOf("스킨케어", Category.SKIN_CARE),
            arrayOf("메이크업", Category.MAKE_UP),
            arrayOf("헤어케어", Category.HAIR_CARE),
            arrayOf("마스크팩", Category.MASK),
            arrayOf("바디케어", Category.BODY_CARE),
            arrayOf("멘즈케어", Category.MENS_CARE),
            arrayOf("네일", Category.NAIL),
            arrayOf("클랜징", Category.CLEANING),
            arrayOf("선케어", Category.SUN_CARE)
        )
    }

    @DisplayName("존재하지 않는 카테고리를 value로 찾으면 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = ["스킨", "unknown", "", "  "])
    fun invalid_value_throws(input: String) {
        assertThatThrownBy { Category.findByValue(input) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}
