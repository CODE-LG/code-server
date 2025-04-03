package codel.member.business

import codel.member.domain.MemberStatus
import codel.member.domain.OauthType
import codel.member.presentation.request.MemberLoginRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberServiceTest(
    @Autowired
    private val memberService: MemberService,
) {
    @DisplayName("첫 로그인을 한 멤버의 상태는 SignUp 이다.")
    @Test
    fun loginMemberSuccessTest() {
        val memberLoginRequest = MemberLoginRequest(OauthType.KAKAO, "hogee")

        val memberStatus = memberService.loginMember(memberLoginRequest).memberStatus

        assertThat(memberStatus).isEqualTo(MemberStatus.SIGNUP)
    }
}
