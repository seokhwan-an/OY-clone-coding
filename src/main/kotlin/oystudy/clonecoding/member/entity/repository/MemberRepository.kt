package oystudy.clonecoding.member.entity.repository

import org.springframework.data.jpa.repository.JpaRepository
import oystudy.clonecoding.member.entity.Member

interface MemberRepository : JpaRepository<Member, Long> {
}
