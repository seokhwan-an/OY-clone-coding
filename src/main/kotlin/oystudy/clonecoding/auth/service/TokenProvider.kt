package oystudy.clonecoding.auth.service

interface TokenProvider {

    fun createAccessToken(memberId: Long): String
}
