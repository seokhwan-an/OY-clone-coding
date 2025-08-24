package oystudy.clonecoding.product.controller

import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import oystudy.clonecoding.product.entity.Category
import oystudy.clonecoding.product.entity.Name
import oystudy.clonecoding.product.entity.Price
import oystudy.clonecoding.product.entity.Product
import oystudy.clonecoding.product.entity.repository.ProductRepository
import oystudy.clonecoding.product.service.dto.CreateRequest
import oystudy.clonecoding.product.service.dto.UpdateRequest
import oystudy.clonecoding.product.service.dto.UpdateResponse
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTest(
    @LocalServerPort
    private val port: Int,

    @Autowired
    private val productRepository: ProductRepository
) {

    @BeforeEach
    fun setUp() {
        RestAssured.port = port
    }

    @AfterEach
    fun deleteAll() {
        productRepository.deleteAll()
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

    @DisplayName("Product 수정")
    @Nested
    inner class Update {

        @DisplayName("수정 성공")
        @Test
        fun success_update() {
            val saveId = productRepository.save(Product(Name.from("아이템"), Price.from(200), Category.HAIR_CARE)).id!!
            val request = UpdateRequest("아이템변경", 100, "스킨케어")

            val updateResponse = given().log().all()
                .contentType(ContentType.JSON)
                .body(request)
                .put("/admin/products/$saveId")
                .then().log().all()
                .statusCode(HttpStatus.OK.value())
                .extract().`as`(UpdateResponse::class.java)

            assertThat(updateResponse.id).isEqualTo(saveId)
            assertThat(updateResponse.name).isEqualTo(request.name)
            assertThat(updateResponse.price).isEqualTo(request.price)
            assertThat(updateResponse.category).isEqualTo(request.category)
        }
    }
}
