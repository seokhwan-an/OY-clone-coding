package oystudy.clonecoding.member.entity

import jakarta.persistence.*
import oystudy.clonecoding.global.entity.BaseTimeEntity

@Entity
@Table(
    name = "member"
)
class Member(
    @Embedded
    val name: Name,

    @Embedded
    val email: Email,

    @Embedded
    val password: Password,

    @Convert(converter = RoleConverter::class)
    @Column(nullable = false)
    val role: Role
) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
