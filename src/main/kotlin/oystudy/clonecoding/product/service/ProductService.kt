package oystudy.clonecoding.product.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import oystudy.clonecoding.product.entity.repository.ProductRepository
import oystudy.clonecoding.product.service.dto.CreateRequest
import oystudy.clonecoding.product.service.dto.UpdateRequest
import oystudy.clonecoding.product.service.dto.UpdateResponse

@Transactional(readOnly = true)
@Service
class ProductService(
    private val productRepository: ProductRepository
) {

    @Transactional
    fun create(request: CreateRequest): Long? {
        val product = request.toEntity()
        return productRepository.save(product).id
    }

    @Transactional
    fun update(id: Long, request: UpdateRequest): UpdateResponse? {
        val product = productRepository.findById(id)
            .orElseThrow { IllegalArgumentException("존재하지 않는 상품입니다.") }
        product.update(request.name, request.price, request.category)
        return UpdateResponse.from(product)
    }
}
