package oystudy.clonecoding.member.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

private const val MAX_LENGTH = 20

@Embeddable
class Name(
    @Column(nullable = false, length = MAX_LENGTH, name = "name")
    var value: String
) {
    init {
        require(value.isNotBlank()) { "이름은 비어 있을 수 없습니다." }
        require(value.length <= MAX_LENGTH) { "이름은 20자 이하여야 합니다." }
    }

    companion object {
        fun from(value: String): Name {
            return Name(value)
        }
    }
}
