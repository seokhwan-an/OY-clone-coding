package oystudy.clonecoding.member.controller

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import oystudy.clonecoding.member.entity.repository.MemberRepository
import oystudy.clonecoding.member.service.dto.RegisterMember
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerTest(
    @LocalServerPort
    private val port: Int,

    @Autowired
    private val memberRepository: MemberRepository
) {

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    @AfterEach
    fun deleteAll() {
        memberRepository.deleteAll()
    }

    @DisplayName("Member 등록 - 사용자")
    @Nested
    inner class RegisterUser {

        @DisplayName("성공")
        @Test
        fun success_register_user() {
            val request = RegisterMember("사용자", "test@test.com", "12345678")
            given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .post("/members/register-user")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
        }
    }

    @DisplayName("Member 등록 - 관리자")
    @Nested
    inner class RegisterAdmin {

        @DisplayName("성공")
        @Test
        fun success_register_admin() {
            val request = RegisterMember("관리자", "test@test.com", "12345678")
            given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .post("/members/register-admin")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
        }
    }
}
