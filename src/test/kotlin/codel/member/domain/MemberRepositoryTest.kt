package codel.member.domain

import codel.config.TestFixture
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberRepositoryTest : TestFixture() {
    @DisplayName("프로필을 저장하면 memberStatus 가 CODE_SURVEY 로 바뀐다.")
    @Test
    fun saveProfileTest() {
        memberRepository.saveProfile(member, nonSavedProfile)
        val findMember = memberRepository.findMember(member.oauthType, member.oauthId)

        assertAll(
            { assertThat(findMember).isNotNull },
            { assertThat(findMember.memberStatus).isEqualTo(MemberStatus.CODE_SURVEY) },
        )
    }

    @DisplayName("멤버 아이디에 해당하는 멤버가 없으면 예외를 반환한다.")
    @Test
    fun saveProfileWithoutMemberTest() {
        val seokMember =
            Member(
                id = 100L,
                oauthType = OauthType.APPLE,
                oauthId = "seok",
            )

        assertThatThrownBy { memberRepository.saveProfile(seokMember, nonSavedProfile) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("멤버가 존재하지 않습니다.")
    }

    @DisplayName("바꾸고자 하는 멤버 아이디가 없으면 예외를 반환한다.")
    @Test
    fun changeMemberStatusWithoutMemberIdTest() {
        assertThatThrownBy { memberRepository.saveProfile(nonSavedMember, nonSavedProfile) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("member id가 비어있습니다.")
    }
}
