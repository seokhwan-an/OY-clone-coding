package oystudy.clonecoding.auth.entity.repository

import org.springframework.data.jpa.repository.JpaRepository
import oystudy.clonecoding.auth.entity.AuthToken

interface AuthTokenRepository : JpaRepository<AuthToken, Long> {

    fun findByMemberIdAndAccessToken(memberId: Long, accessToken: String): AuthToken?

    fun findByMemberId(memberId: Long): AuthToken?
}
