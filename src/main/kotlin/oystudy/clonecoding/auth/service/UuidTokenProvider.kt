package oystudy.clonecoding.auth.service

import org.springframework.stereotype.Component
import java.util.*

@Component
class UuidTokenProvider : TokenProvider {
    override fun createAccessToken(memberId: Long): String {
        return UUID.randomUUID().toString()
    }
}
