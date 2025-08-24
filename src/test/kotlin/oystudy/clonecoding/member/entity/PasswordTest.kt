package oystudy.clonecoding.member.entity

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

class PasswordTest {

    @DisplayName("생성 실패 - 빈 값")
    @Test
    fun fail_create_with_empty() {
        assertThatThrownBy { Password("") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("비밀번호는 비어 있을 수 없습니다.")
    }

    @DisplayName("생성 실패 - 최소 길이 8자")
    @Test
    fun fail_create_with_length_under_min() {
        val value = "1234567"
        assertThatThrownBy { Password(value) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("비밀번호는 8자 이상이어야 합니다.")
    }

    @DisplayName("생성 실패 - 최대 길이 20자")
    @Test
    fun fail_create_with_length_over_max() {
        val value = "123456789012345678901234567890123456789012345678901234567890"
        assertThatThrownBy { Password(value) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("비밀번호는 20자 이하여야 합니다.")
    }

    @DisplayName("생성 성공")
    @Test
    fun success_create() {
        val value = "12345678"
        val password = Password(value)
        assertThat(password.value).isEqualTo(value)
    }
}
