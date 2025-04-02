package codel.member.business

import codel.member.domain.Member
import codel.member.domain.MemberRepository
import codel.member.domain.Profile
import codel.member.presentation.request.MemberSavedRequest
import codel.member.presentation.request.ProfileSavedRequest
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

    fun saveProfile(request: ProfileSavedRequest) {
        val profile =
            Profile(
                codeName = request.codeName,
                age = request.age,
                job = request.job,
                alcohol = request.alcohol,
                smoke = request.smoke,
                hobby = request.hobby,
                style = request.style,
                bigCity = request.bigCity,
                smallCity = request.smallCity,
                mbti = request.mbti,
                introduce = request.introduce,
            )
        memberRepository.saveProfile(profile)
    }
}
