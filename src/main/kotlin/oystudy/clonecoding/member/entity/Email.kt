package oystudy.clonecoding.member.entity

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

private val EMAIL_REGEX = Regex("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

@Embeddable
class Email(
    @Column(nullable = false, name = "email")
    var value: String
) {
    init {
        require(value.isNotBlank()) { "이메일은 비어 있을 수 없습니다." }
        require(value.matches(EMAIL_REGEX)) { "이메일은 유효한 형식이어야 합니다." }
    }
}
