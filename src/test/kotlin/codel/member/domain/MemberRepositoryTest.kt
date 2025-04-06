package codel.member.domain

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberRepositoryTest(
    @Autowired
    private val memberRepository: MemberRepository,
) {
    @DisplayName("멤버 아이디가 없으면 예외를 반환한다.")
    @Test
    fun saveProfileWithoutMemberIdTest() {
        val seokMember = createMemberWithoutMemberId()

        val seokProfile = createProfile()

        assertThatThrownBy { memberRepository.saveProfile(seokMember, seokProfile) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("member id가 비어있습니다.")
    }

    @DisplayName("멤버 아이디에 해당하는 멤버가 없으면 예외를 반환한다.")
    @Test
    fun saveProfileWithoutMemberTest() {
        val seokMember = createMember()
        val seokProfile = createProfile()

        assertThatThrownBy { memberRepository.saveProfile(seokMember, seokProfile) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("멤버가 존재하지 않습니다.")
    }

    @DisplayName("바꾸고자 하는 멤버 아이디가 없으면 예외를 반환한다.")
    @Test
    fun changeMemberStatusWithoutMemberIdTest() {
        val seokMember = createMemberWithoutMemberId()

        assertThatThrownBy { memberRepository.changeMemberStatus(seokMember, MemberStatus.CODE_SURVEY) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("member id가 비어있습니다.")
    }

    @DisplayName("바꾸고자 하는 멤버 아이디에 해당하는 멤버가 없으면 예외를 반환한다.")
    @Test
    fun changeMemberStatusWithoutMemberTest() {
        val seokMember = createMember()

        assertThatThrownBy { memberRepository.changeMemberStatus(seokMember, MemberStatus.CODE_SURVEY) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("멤버가 존재하지 않습니다.")
    }

    private fun createMemberWithoutMemberId(): Member =
        Member(
            oauthType = OauthType.APPLE,
            oauthId = "seok",
        )

    private fun createMember(): Member =
        Member(
            id = 100L,
            oauthType = OauthType.APPLE,
            oauthId = "seok",
        )

    private fun createProfile(): Profile =
        Profile(
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
}
