package oystudy.clonecoding.member.entity

enum class Role(
    val value: String
) {
    USER("사용자"),
    ADMIN("관리자");

    companion object {
        fun findByValue(value: String): Role =
            entries.find { it.value == value }
                ?: throw IllegalArgumentException("존재하지 않는 역할 입니다.")
    }
}
