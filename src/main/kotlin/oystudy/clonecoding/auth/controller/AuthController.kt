package oystudy.clonecoding.auth.controller

import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import oystudy.clonecoding.auth.service.AuthService
import oystudy.clonecoding.auth.service.dto.LoginRequest
import oystudy.clonecoding.auth.service.dto.LoginResponse

@RequestMapping("/auth")
@RestController
class AuthController(
    private val authService: AuthService,
    private val cookieProvider: CookieProvider
) {

    @PostMapping("/login")
    fun login(request: LoginRequest, response: HttpServletResponse): ResponseEntity<LoginResponse> {
        val accessToken = authService.createAccessToken(request)
        val cookie = cookieProvider.createAuthCookie(accessToken)
        response.addCookie(cookie)
        return ResponseEntity.ok(LoginResponse(accessToken))
    }
}
