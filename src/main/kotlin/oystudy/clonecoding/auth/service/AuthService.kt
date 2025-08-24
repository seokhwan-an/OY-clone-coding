package oystudy.clonecoding.auth.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import oystudy.clonecoding.auth.entity.AuthToken
import oystudy.clonecoding.auth.entity.repository.AuthTokenRepository
import oystudy.clonecoding.auth.service.dto.LoginRequest
import oystudy.clonecoding.member.entity.Email
import oystudy.clonecoding.member.entity.Password
import oystudy.clonecoding.member.entity.repository.MemberRepository

@Transactional(readOnly = true)
@Service
class AuthService(
    private val authTokenRepository: AuthTokenRepository,
    private val memberRepository: MemberRepository,
    private val tokenProvider: TokenProvider
) {

    @Transactional
    fun createAccessToken(request: LoginRequest): String {
        val member = memberRepository.findByEmailAndPassword(Email.from(request.email), Password.from(request.password))
            ?: throw IllegalArgumentException("존재하지 않는 유저입니다.")

        val authToken = authTokenRepository.findByMemberId(member.id!!)
        require(authToken == null) { "이미 로그인 중입니다." }

        // TODO : jwt 기반 방식으로 변경하기
        val accessToken = tokenProvider.createAccessToken(member.id!!)
        authTokenRepository.save(AuthToken(member.id!!, accessToken))
        return accessToken
    }
}
