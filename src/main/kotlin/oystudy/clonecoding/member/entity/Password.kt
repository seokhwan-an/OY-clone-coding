package oystudy.clonecoding.member.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

private const val MIN_LENGTH = 8
private const val MAX_LENGTH = 20

@Embeddable
class Password(

    @Column(nullable = false, length = MIN_LENGTH, name = "password")
    val value: String
) {
    init {
        require(value.isNotBlank()) { "비밀번호는 비어 있을 수 없습니다." }
        require(value.length >= MIN_LENGTH) { "비밀번호는 8자 이상이어야 합니다." }
        require(value.length <= MAX_LENGTH) { "비밀번호는 20자 이하여야 합니다." }
    }

    companion object {
        fun from(value: String): Password {
            return Password(value)
        }
    }
}
