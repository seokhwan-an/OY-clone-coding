package oystudy.clonecoding.product.service.dto

import oystudy.clonecoding.product.entity.Category
import oystudy.clonecoding.product.entity.Name
import oystudy.clonecoding.product.entity.Price
import oystudy.clonecoding.product.entity.Product

data class CreateRequest(
    val name: String,
    val price: Int,
    val category: String
) {
    fun toEntity(): Product = Product(
        Name.from(name),
        Price.from(price),
        Category.findByValue(category)
    )
}
