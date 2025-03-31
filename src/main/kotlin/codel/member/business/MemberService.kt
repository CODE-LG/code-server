package codel.member.business

import codel.member.domain.Member
import codel.member.domain.MemberRepository
import codel.member.presentation.request.MemberSavedRequest
import codel.member.presentation.response.MemberSavedResponse
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {
    fun saveMember(request: MemberSavedRequest): MemberSavedResponse {
        val member =
            Member(
                oauthType = request.oauthType,
                oauthId = request.oauthId,
            )
        val savedMember = memberRepository.saveMember(member)

        return MemberSavedResponse(savedMember.memberStatus)
    }
}
