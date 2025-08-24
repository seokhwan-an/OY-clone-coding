package oystudy.clonecoding.member.entity

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

class EmailTest {

    @DisplayName("Email 생성 실패 - 빈 값")
    @Test
    fun fail_create_with_empty() {
        Assertions.assertThatThrownBy { Email("") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("이메일은 비어 있을 수 없습니다.")
    }

    @DisplayName("Email 생성 실패 - 유효하지 않은 형식")
    @Test
    fun fail_create_with_invalid() {
        Assertions.assertThatThrownBy { Email("test@test") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("이메일은 유효한 형식이어야 합니다.")
    }

    @DisplayName("Email 생성 성공")
    @Test
    fun success_create() {
        val email = Email("test@test.com")
        assertThat(email.value).isEqualTo("test@test.com")
    }
}
