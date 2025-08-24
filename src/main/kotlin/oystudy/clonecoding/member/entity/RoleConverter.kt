package oystudy.clonecoding.member.entity

import jakarta.persistence.AttributeConverter

class RoleConverter: AttributeConverter<Role, String> {
    override fun convertToDatabaseColumn(attribute: Role?): String {
        return attribute?.value ?: throw IllegalArgumentException("역할은 null일 수 없습니다.")
    }

    override fun convertToEntityAttribute(dbData: String?): Role {
        return dbData?.let { Role.findByValue(it) } ?: throw IllegalArgumentException("역할은 null일 수 없습니다.")
    }
}
