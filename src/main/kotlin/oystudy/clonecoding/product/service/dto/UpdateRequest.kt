package oystudy.clonecoding.product.service.dto

data class UpdateRequest(
    val name: String,
    val price: Int,
    val category: String
)
