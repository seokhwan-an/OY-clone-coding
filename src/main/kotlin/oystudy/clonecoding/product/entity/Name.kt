package oystudy.clonecoding.product.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

private const val LENGTH = 50

@Embeddable
data class Name(
    @Column(nullable = false, length = LENGTH, name = "name")
    var value: String
) {
    init {
        validate(value)
    }

    private fun validate(v: String) {
        require(v.isNotBlank()) { "상품명은 비어 있을 수 없습니다." }
        require(v.length <= LENGTH) { "상품명은 50자 이하여야 합니다." }
    }

    companion object {
        fun from(value: String): Name {
            return Name(value)
        }
    }

    fun update(request: String) {
        validate(request)
        this.value = request
    }
}
