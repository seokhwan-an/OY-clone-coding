package oystudy.clonecoding.member.entity

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

class NameTest {

    @DisplayName("생성 실패 - 이름 공백 혹은 빈칸")
    @Test
    fun fail_create_name_with_empty_blank() {
        assertThatThrownBy { Name("") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("이름은 비어 있을 수 없습니다.")
    }

    @DisplayName("생성 실패 - 이름 길이 초과")
    @Test
    fun fail_create_name_with_length_exceed() {
        val value = "아".repeat(21)
        assertThatThrownBy { Name(value) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("이름은 20자 이하여야 합니다.")
    }

    @DisplayName("생성 성공")
    @Test
    fun success_create() {
        val name = Name("이름")
        assertThat(name.value).isEqualTo("이름")
    }
}
