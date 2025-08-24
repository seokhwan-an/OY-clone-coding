package oystudy.clonecoding.auth.entity

import jakarta.persistence.*

@Entity
@Table(
    name = "auth_token"
)
class AuthToken(

    @Column(nullable = false, name = "member_id")
    val memberId: Long,

    @Column(nullable = false, name = "access_token")
    val accessToken: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
}
