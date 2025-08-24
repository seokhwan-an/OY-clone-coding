package oystudy.clonecoding.product.service

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import oystudy.clonecoding.product.entity.Category
import oystudy.clonecoding.product.entity.Name
import oystudy.clonecoding.product.entity.Price
import oystudy.clonecoding.product.entity.Product
import oystudy.clonecoding.product.entity.repository.ProductRepository
import oystudy.clonecoding.product.service.dto.CreateRequest
import oystudy.clonecoding.product.service.dto.UpdateRequest
import java.math.BigDecimal

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

    @DisplayName("Product 수정")
    @Nested
    inner class Update {

        @DisplayName("수정 실패 - 존재하지 않는 상품")
        @Test
        fun fail_update_with_unknown() {
            val wrongId = 0L
            val request = UpdateRequest("아이템", 100, "스킨케어")
            Assertions.assertThatThrownBy { productService.update(wrongId, request) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("존재하지 않는 상품입니다.")
        }

        @DisplayName("수정 실패 - 상품 이름 공백 혹은 빈칸")
        @Test
        fun fail_update_name_with_empty_blank() {
            val saveId = productRepository.save(Product(Name.from("아이템"), Price.from(200), Category.HAIR_CARE)).id!!
            val requestWithBlankName = UpdateRequest("", 100, "스킨케어")
            Assertions.assertThatThrownBy { productService.update(saveId, requestWithBlankName) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("상품명은 비어 있을 수 없습니다.")
        }

        @DisplayName("수정 실패 - 상품 이름 길이 초과")
        @Test
        fun fail_update_name_with_length_exceed() {
            val saveId = productRepository.save(Product(Name.from("아이템"), Price.from(200), Category.HAIR_CARE)).id!!
            val requestWithExceedNameLength = UpdateRequest("아".repeat(51), 100, "스킨케어")
            Assertions.assertThatThrownBy { productService.update(saveId, requestWithExceedNameLength) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("상품명은 50자 이하여야 합니다.")
        }

        @DisplayName("수정 실패 - 상품 가격0 미만의 값")
        @Test
        fun fail_update_price_with_zero() {
            val saveId = productRepository.save(Product(Name.from("아이템"), Price.from(200), Category.HAIR_CARE)).id!!
            val requestWithPriceUnderZero = UpdateRequest("아이템", 0, "스킨케어")
            Assertions.assertThatThrownBy { productService.update(saveId, requestWithPriceUnderZero) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("상품 가격은 0원보다 커야 합니다.")
        }

        @DisplayName("수정 실패 - 상품 가격500,000 초과의 값")
        @Test
        fun fail_update_price_with_max() {
            val saveId = productRepository.save(Product(Name.from("아이템"), Price.from(200), Category.HAIR_CARE)).id!!
            val requestWithPriceOverMax = UpdateRequest("아이템", 500_001, "스킨케어")
            Assertions.assertThatThrownBy { productService.update(saveId, requestWithPriceOverMax) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("상품 가격은 500,000원보다 이하여야 합니다.")
        }

        @DisplayName("수정 실패 - 카테고리 존재하지 않는 값")
        @Test
        fun fail_update_category_with_unknown() {
            val saveId = productRepository.save(Product(Name.from("아이템"), Price.from(200), Category.HAIR_CARE)).id!!
            val requestWithWrongCategory = UpdateRequest("아이템", 100, "unknown")
            Assertions.assertThatThrownBy { productService.update(saveId, requestWithWrongCategory) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage("존재하지 않는 카테고리 입니다.")
        }

        @DisplayName("수정 성공")
        @Test
        fun success_update() {
            val saveId = productRepository.save(Product(Name.from("아이템"), Price.from(200), Category.HAIR_CARE)).id!!
            val request = UpdateRequest("아이템변경", 100, "스킨케어")

            productService.update(saveId, request)!!
            val updatedProduct = productRepository.findById(saveId).get()

            Assertions.assertThat(updatedProduct.id).isEqualTo(saveId)
            Assertions.assertThat(updatedProduct.name.value).isEqualTo(request.name)
            Assertions.assertThat(updatedProduct.price.value.toBigInteger()).isEqualTo(request.price)
            Assertions.assertThat(updatedProduct.category.value).isEqualTo(request.category)
        }
    }
}
