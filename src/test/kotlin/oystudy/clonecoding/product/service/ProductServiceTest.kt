package oystudy.clonecoding.product.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import oystudy.clonecoding.product.entity.repository.ProductRepository
import oystudy.clonecoding.product.service.dto.CreateRequest

@SpringBootTest
class ProductServiceTest(
    @Autowired
    private val productRepository: ProductRepository,
    @Autowired
    private val productService: ProductService
) {

    @AfterEach
    fun deleteAll() {
        productRepository.deleteAll()
    }

    @DisplayName("Product 생성")
    @Nested
    inner class Create {

        @DisplayName("생성 실패 - 상품 이름 공백 혹은 빈칸")
        @Test
        fun fail_create_name_with_empty_blank() {
            val request = CreateRequest("", 0, "스킨케어")
            Assertions.assertThatThrownBy { productService.create(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("상품명은 비어 있을 수 없습니다.")
        }

        @DisplayName("생성 실패 - 상품 이름 길이 초과")
        @Test
        fun fail_create_name_with_length_exceed() {
            val request = CreateRequest("아".repeat(51), 0, "스킨케어")
            Assertions.assertThatThrownBy { productService.create(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("상품명은 50자 이하여야 합니다.")
        }

        @DisplayName("생성 실패 - 상품 가격0 미만의 값")
        @Test
        fun fail_create_price_with_zero() {
            val request = CreateRequest("아이템", 0, "스킨케어")
            Assertions.assertThatThrownBy { productService.create(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("상품 가격은 0원보다 커야 합니다.")
        }

        @DisplayName("생성 실패 - 상품 가격500,000 초과의 값")
        @Test
        fun fail_create_price_with_max() {
            val request = CreateRequest("아이템", 500_001, "스킨케어")
            Assertions.assertThatThrownBy { productService.create(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("상품 가격은 500,000원보다 이하여야 합니다.")
        }

        @DisplayName("생성 실패 - 카테고리 존재하지 않는 값")
        @Test
        fun fail_create_category_with_unknown() {
            val request = CreateRequest("아이템", 100, "unknown")
            Assertions.assertThatThrownBy { productService.create(request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("존재하지 않는 카테고리 입니다.")
        }

        @DisplayName("생성 성공")
        @Test
        fun success_create() {
            val request = CreateRequest("아이템", 100, "스킨케어")
            val actual = productService.create(request)
            Assertions.assertThat(actual).isNotNull()
        }
    }
}
