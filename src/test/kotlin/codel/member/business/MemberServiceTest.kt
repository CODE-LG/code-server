package codel.member.business

import codel.config.TestFixture
import codel.member.domain.MemberStatus
import codel.member.domain.OauthType
import codel.member.presentation.request.MemberLoginRequest
import codel.member.presentation.request.ProfileSavedRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberServiceTest(
    @Autowired
    private val memberService: MemberService,
) : TestFixture() {
    @DisplayName("첫 로그인을 한 멤버의 상태는 SignUp 이다.")
    @Test
    fun loginMemberSuccessTest() {
        val memberLoginRequest =
            MemberLoginRequest(
                oauthType = OauthType.KAKAO,
                oauthId = "hogee",
            )

        val memberStatus = memberService.loginMember(memberLoginRequest).memberStatus

        assertThat(memberStatus).isEqualTo(MemberStatus.SIGNUP)
    }

    @DisplayName("프로필을 저장에 성공한 후 멤버 상태는 CODE_SURVEY 이다.")
    @Test
    fun saveProfileSuccessTest() {
        val profileSavedRequest =
            ProfileSavedRequest(
                codeName = "seok",
                age = 28,
                job = "백엔드 개발자",
                alcohol = "자주 마심",
                smoke = "비흡연자 - 흡연자와 교류 NO",
                hobby = listOf("영화 & 드라마", "여행 & 캠핑"),
                style = listOf("표현을 잘하는 직진형", "상대가 필요할 때 항상 먼저 연락하는 스타일"),
                bigCity = "경기도",
                smallCity = "성남시",
                mbti = "isfj",
                introduce = "잘부탁드립니다!",
            )
        memberService.saveProfile(memberSignup, profileSavedRequest)

        val findMember =
            memberService.findMember(
                oauthType = memberSignup.oauthType,
                oauthId = memberSignup.oauthId,
            )

        Assertions.assertAll(
            { assertThat(findMember.profile).isNotNull },
            { assertThat(findMember.memberStatus).isEqualTo(MemberStatus.CODE_SURVEY) },
        )
    }
}
