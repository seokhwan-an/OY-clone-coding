package oystudy.clonecoding.member.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import oystudy.clonecoding.member.entity.Email
import oystudy.clonecoding.member.entity.repository.MemberRepository
import oystudy.clonecoding.member.service.dto.RegisterMember

@Transactional(readOnly = true)
@Service
class MemberService(
    private val memberRepository: MemberRepository
) {

    @Transactional
    fun registerUser(request: RegisterMember) : Long? {
        validateDuplicateEmail(request.email)
        val UserMember = request.toUser()
        return memberRepository.save(UserMember).id
    }

    @Transactional
    fun registerAdmin(request: RegisterMember) : Long?{
        validateDuplicateEmail(request.email)
        val AdminMember = request.toAdmin()
        return memberRepository.save(AdminMember).id
    }

    private fun validateDuplicateEmail(email: String) {
        val findByEmail = memberRepository.findByEmail(Email.from(email))
        require(findByEmail == null) { "이미 존재하는 이메일입니다." }
    }
}
