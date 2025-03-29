package codel.auth.business

import codel.auth.TokenProvider
import codel.member.domain.MemberRepository
import codel.member.presentation.request.MemberSavedRequest
import org.springframework.stereotype.Service

@Service
class AuthService(
    val tokenProvider: TokenProvider,
    val memberRepository: MemberRepository,
) {
    fun provideToken(request: MemberSavedRequest): String {
        val member = memberRepository.findMember(request.oauthType, request.oauthId)
        return tokenProvider.provide(member)
    }
}
