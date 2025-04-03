package codel.auth.business

import codel.auth.TokenProvider
import codel.member.domain.MemberRepository
import codel.member.presentation.request.MemberLoginRequest
import org.springframework.stereotype.Service

@Service
class AuthService(
    val tokenProvider: TokenProvider,
    val memberRepository: MemberRepository,
) {
    fun provideToken(request: MemberLoginRequest): String {
        val member = memberRepository.findMember(request.oauthType, request.oauthId)
        return tokenProvider.provide(member)
    }
}
