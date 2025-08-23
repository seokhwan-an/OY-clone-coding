package oystudy.clonecoding.product.entity

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter(autoApply = true)
class CategoryConverter : AttributeConverter<Category, String> {
    override fun convertToDatabaseColumn(attribute: Category?): String {
        return attribute?.value ?: throw IllegalArgumentException("카테고리는 null일 수 없습니다.")
    }

    override fun convertToEntityAttribute(dbData: String?): Category {
        return dbData?.let { Category.findByValue(it) } ?: throw IllegalArgumentException("카테고리는 null일 수 없습니다.")
    }
}
