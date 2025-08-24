package oystudy.clonecoding.product.service.dto

import oystudy.clonecoding.product.entity.Product

data class UpdateResponse(
    val id: Long,
    val name: String,
    val price: Int,
    val category: String
) {
    companion object {
        fun from(product: Product): UpdateResponse {
            return UpdateResponse(
                product.id ?: throw IllegalArgumentException("존재하는 상품이 아닙니다."),
                product.name.value,
                product.price.value.toInt(),
                product.category.value
            )
        }
    }
}
