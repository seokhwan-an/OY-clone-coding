package oystudy.clonecoding.member.entity.repository

import org.springframework.data.jpa.repository.JpaRepository
import oystudy.clonecoding.member.entity.Email
import oystudy.clonecoding.member.entity.Member
import oystudy.clonecoding.member.entity.Password

interface MemberRepository : JpaRepository<Member, Long> {

    fun findByEmail(email: Email): Member?

    fun findByEmailAndPassword(email: Email, password: Password): Member?
}
