package oystudy.clonecoding.product.controller

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import oystudy.clonecoding.product.service.dto.CreateRequest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest(
    @LocalServerPort
    private val port: Int
) {

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    @DisplayName("Product 생성")
    @Nested
    inner class Create {

        @DisplayName("생성 성공")
        @Test
        fun success_create() {
            val request = CreateRequest("아이템", 100, "스킨케어")

            given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .post("/admin/products")
                .then().log().all()
                .statusCode(HttpStatus.CREATED.value())
        }
    }
}
