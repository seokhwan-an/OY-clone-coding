package oystudy.clonecoding.member.service

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import oystudy.clonecoding.member.entity.*
import oystudy.clonecoding.member.entity.repository.MemberRepository
import oystudy.clonecoding.member.service.dto.RegisterMember
import kotlin.test.Test

@SpringBootTest
class MemberServiceTest(
    @Autowired
    private val memberRepository: MemberRepository,
    @Autowired
    private val memberService: MemberService
) {

    @AfterEach
    fun deleteAll() {
        memberRepository.deleteAll()
    }

    @DisplayName("Member 등록 - 사용자")
    @Nested
    inner class RegisterUser {

        @DisplayName("등록 성공")
        @Test
        fun success_register_user() {
            val request = RegisterMember("사용자", "test@test.com", "12345678")
            val actual = memberService.registerUser(request)!!

            val user = memberRepository.findById(actual).get()
            assertThat(user.name.value).isEqualTo(request.name)
            assertThat(user.email.value).isEqualTo(request.email)
            assertThat(user.password.value).isEqualTo(request.password)
            assertThat(user.role).isEqualTo(Role.USER)
        }

        @DisplayName("등록 실패 - 이메일 형식 오류")
        @Test
        fun fail_register_user_with_invalid_email() {
            val request = RegisterMember("사용자", "test@test", "12345678")
            assertThatThrownBy { memberService.registerUser(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("이메일은 유효한 형식이어야 합니다.")
        }

        @DisplayName("등록 실패 - 이메일 빈 값")
        @Test
        fun fail_register_user_with_empty_email() {
            val request = RegisterMember("사용자", "", "12345678")
            assertThatThrownBy { memberService.registerUser(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("이메일은 비어 있을 수 없습니다.")
        }

        @DisplayName("등록 실패 - 비밀번호 빈 값")
        @Test
        fun fail_register_user_with_empty_password() {
            val request = RegisterMember("사용자", "test@test.com", "")
            assertThatThrownBy { memberService.registerUser(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("비밀번호는 비어 있을 수 없습니다.")
        }

        @DisplayName("등록 실패 - 비밀번호 최소 길이 8자")
        @Test
        fun fail_register_user_with_length_under_min_password() {
            val request = RegisterMember("사용자", "test@test.com", "1234567")
            assertThatThrownBy { memberService.registerUser(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("비밀번호는 8자 이상이어야 합니다.")
        }

        @DisplayName("등록 실패 - 비밀번호 최대 길이 20자")
        @Test
        fun fail_register_user_with_length_over_max_password() {
            val request =
                RegisterMember("사용자", "test@test.com", "123456789012345678901234567890123456789012345678901234567890")
            assertThatThrownBy { memberService.registerUser(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("비밀번호는 20자 이하여야 합니다.")
        }

        @DisplayName("등록 실패 - 이름 공백 혹은 빈칸")
        @Test
        fun fail_register_user_with_empty_name() {
            val request = RegisterMember("", "test@test.com", "12345678")
            assertThatThrownBy { memberService.registerUser(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("이름은 비어 있을 수 없습니다.")
        }

        @DisplayName("등록 실패 - 이름 길이 초과")
        @Test
        fun fail_register_user_with_length_exceed_name() {
            val request = RegisterMember("아".repeat(21), "test@test.com", "12345678")
            assertThatThrownBy { memberService.registerUser(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("이름은 20자 이하여야 합니다.")
        }

        @DisplayName("등록 실패 - 이미 존재하는 이메일")
        @Test
        fun fail_register_user_with_existing_email() {
            memberRepository.save(
                Member(
                    Name.from("사용자"),
                    Email.from("test@test.com"),
                    Password.from("12345678"),
                    Role.USER
                )
            )
            val request = RegisterMember("사용자", "test@test.com", "12345678")
            assertThatThrownBy { memberService.registerUser(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("이미 존재하는 이메일입니다.")
        }
    }

    @DisplayName("Member 둥록 - 관리자")
    @Nested
    inner class RegisterAdmin {

        @DisplayName("등록 성공")
        @Test
        fun success_register_admin() {
            val request = RegisterMember("관리자", "test@test.com", "12345678")
            val actual = memberService.registerAdmin(request)!!

            val admin = memberRepository.findById(actual).get()
            assertThat(admin.name.value).isEqualTo(request.name)
            assertThat(admin.email.value).isEqualTo(request.email)
            assertThat(admin.password.value).isEqualTo(request.password)
            assertThat(admin.role).isEqualTo(Role.ADMIN)
        }

        @DisplayName("등록 실패 - 이메일 형식 오류")
        @Test
        fun fail_register_user_with_invalid_email() {
            val request = RegisterMember("사용자", "test@test", "12345678")
            assertThatThrownBy { memberService.registerAdmin(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("이메일은 유효한 형식이어야 합니다.")
        }

        @DisplayName("등록 실패 - 이메일 빈 값")
        @Test
        fun fail_register_user_with_empty_email() {
            val request = RegisterMember("사용자", "", "12345678")
            assertThatThrownBy { memberService.registerAdmin(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("이메일은 비어 있을 수 없습니다.")
        }

        @DisplayName("등록 실패 - 비밀번호 빈 값")
        @Test
        fun fail_register_user_with_empty_password() {
            val request = RegisterMember("사용자", "test@test.com", "")
            assertThatThrownBy { memberService.registerAdmin(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("비밀번호는 비어 있을 수 없습니다.")
        }

        @DisplayName("등록 실패 - 비밀번호 최소 길이 8자")
        @Test
        fun fail_register_user_with_length_under_min_password() {
            val request = RegisterMember("사용자", "test@test.com", "1234567")
            assertThatThrownBy { memberService.registerAdmin(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("비밀번호는 8자 이상이어야 합니다.")
        }

        @DisplayName("등록 실패 - 비밀번호 최대 길이 20자")
        @Test
        fun fail_register_user_with_length_over_max_password() {
            val request =
                RegisterMember("사용자", "test@test.com", "123456789012345678901234567890123456789012345678901234567890")
            assertThatThrownBy { memberService.registerAdmin(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("비밀번호는 20자 이하여야 합니다.")
        }

        @DisplayName("등록 실패 - 이름 공백 혹은 빈칸")
        @Test
        fun fail_register_user_with_empty_name() {
            val request = RegisterMember("", "test@test.com", "12345678")
            assertThatThrownBy { memberService.registerAdmin(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("이름은 비어 있을 수 없습니다.")
        }

        @DisplayName("등록 실패 - 이름 길이 초과")
        @Test
        fun fail_register_user_with_length_exceed_name() {
            val request = RegisterMember("아".repeat(21), "test@test.com", "12345678")
            assertThatThrownBy { memberService.registerAdmin(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("이름은 20자 이하여야 합니다.")
        }

        @DisplayName("등록 실패 - 이미 존재하는 이메일")
        @Test
        fun fail_register_user_with_existing_email() {
            memberRepository.save(
                Member(
                    Name.from("관리자"),
                    Email.from("test@test.com"),
                    Password.from("12345678"),
                    Role.ADMIN
                )
            )
            val request = RegisterMember("관리자", "test@test.com", "12345678")
            assertThatThrownBy { memberService.registerAdmin(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("이미 존재하는 이메일입니다.")
        }
    }
}
