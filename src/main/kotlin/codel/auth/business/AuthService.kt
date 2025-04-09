package codel.auth.business

import codel.auth.TokenProvider
import codel.member.business.MemberService
import codel.member.presentation.request.MemberLoginRequest
import org.springframework.stereotype.Service

@Service
class AuthService(
    val tokenProvider: TokenProvider,
    val memberService: MemberService,
) {
    fun provideToken(request: MemberLoginRequest): String {
        val member = memberService.findMember(request.oauthType, request.oauthId)
        return tokenProvider.provide(member)
    }
}
