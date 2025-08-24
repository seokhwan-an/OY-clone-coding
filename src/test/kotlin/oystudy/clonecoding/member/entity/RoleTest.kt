package oystudy.clonecoding.member.entity

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import kotlin.test.Test

class RoleTest {

    @DisplayName("존재하지 않는 역할 입니다.")
    @Test
    fun fail_find_by_value_with_unknown() {
        assertThatThrownBy { Role.findByValue("unknown") }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("존재하지 않는 역할 입니다.")
    }

    @DisplayName("역할 찾기 성공")
    @Test
    fun success_find_by_value() {
        val role = Role.findByValue("사용자")
        assertThat(role).isEqualTo(Role.USER)
    }
}
