package oystudy.clonecoding.member.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import oystudy.clonecoding.member.service.MemberService
import oystudy.clonecoding.member.service.dto.RegisterMember
import java.net.URI

@RequestMapping("/members")
@RestController
class MemberController(
    private val memberService: MemberService
) {

    @PostMapping("/register-user")
    fun registerUser(@RequestBody request: RegisterMember): ResponseEntity<Void> {
        val create = memberService.registerUser(request)
        return ResponseEntity.created(URI("/members/$create"))
            .build()
    }

    @PostMapping("/register-admin")
    fun registerAdmin(@RequestBody request: RegisterMember): ResponseEntity<Void> {
        val create = memberService.registerAdmin(request)
        return ResponseEntity.created(URI("/members/$create"))
            .build()
    }
}
