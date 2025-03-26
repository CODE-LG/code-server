package codel.business

import codel.domain.Member
import codel.domain.MemberRepository
import codel.presentation.request.MemberSavedRequest
import codel.presentation.response.MemberSavedResponse
import org.springframework.stereotype.Service

@Service
class MemberService(
        private val memberRepository: MemberRepository
) {

    fun saveMember(
            request: MemberSavedRequest
    ): MemberSavedResponse {
        val member = Member(
                oauthType = request.oauthType,
                oauthId = request.oauthId
        )
        val isUser = memberRepository.saveMember(member)

        return MemberSavedResponse(isUser)
    }
}
