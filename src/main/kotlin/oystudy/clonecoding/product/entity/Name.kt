package oystudy.clonecoding.product.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

private const val LENGTH = 50

@Embeddable
data class Name(
    @Column(nullable = false, length = LENGTH, name = "name")
    val value: String
) {
    init {
        require(value.isNotBlank()) { "상품명은 비어 있을 수 없습니다." }
        require(value.length <= LENGTH) {"상품명은 50자 이하여야 합니다."}
    }
}
