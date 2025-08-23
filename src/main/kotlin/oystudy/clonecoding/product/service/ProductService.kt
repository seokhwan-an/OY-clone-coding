package oystudy.clonecoding.product.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import oystudy.clonecoding.product.entity.repository.ProductRepository
import oystudy.clonecoding.product.service.dto.CreateRequest

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
}
