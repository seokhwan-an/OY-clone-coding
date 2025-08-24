package oystudy.clonecoding.member.service.dto

import oystudy.clonecoding.member.entity.*


data class RegisterMember(
    val name: String,
    val email: String,
    val password: String
) {
    fun toUser(): Member = Member(
        Name.from(name),
        Email.from(email),
        Password.from(password),
        Role.USER
    )

    fun toAdmin() : Member = Member(
        Name.from(name),
        Email.from(email),
        Password.from(password),
        Role.ADMIN
    )
}
