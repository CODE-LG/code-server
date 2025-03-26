package codel.business

import codel.domain.OauthType
import codel.presentation.request.MemberSavedRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberServiceTest(
        @Autowired
        private val memberService: MemberService
) {

    @DisplayName("멤버 중복 저장 테스트")
    @Test
    fun saveMemberSuccessTest() {
        val memberSavedRequest = MemberSavedRequest(OauthType.KAKAO, "hogee")

        val newUser = memberService.saveMember(memberSavedRequest).isUser
        val duplicatedUser = memberService.saveMember(memberSavedRequest).isUser

        assertThat(newUser).isFalse()
        assertThat(duplicatedUser).isTrue()
    }
}
