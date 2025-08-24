package oystudy.clonecoding.auth.controller

import jakarta.servlet.http.Cookie
import org.springframework.stereotype.Component

private const val WEEK = 7 * 24 * 60 * 60

@Component
class CookieProvider {

    fun createAuthCookie(token: String): Cookie {
        val cookie = Cookie("accessToken", token)
        cookie.maxAge = WEEK
        cookie.isHttpOnly = true
        cookie.secure = true
        return cookie
    }
}
