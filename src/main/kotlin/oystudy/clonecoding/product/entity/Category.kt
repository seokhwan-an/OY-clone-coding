package oystudy.clonecoding.product.entity

enum class Category(val value: String) {

    SKIN_CARE("스킨케어"),
    MAKE_UP("메이크업"),
    HAIR_CARE("헤어케어"),
    MASK("마스크팩"),
    BODY_CARE("바디케어"),
    MENS_CARE("멘즈케어"),
    NAIL("네일"),
    CLEANING("클랜징"),
    SUN_CARE("선케어");

    companion object {
        fun findByValue(value: String): Category =
            entries.find { it.value == value }
                ?: throw IllegalArgumentException("존재하지 않는 카테고리 입니다.")
    }
}
