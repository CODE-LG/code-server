package codel.member.domain

import codel.config.TestFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberRepositoryTest : TestFixture() {
    @DisplayName("프로필 저장 테스트")
    @Test
    fun saveProfileTest() {
        val seokProfile =
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

        memberRepository.saveProfile(hogee, seokProfile)
        val findMember = memberRepository.findMember(hogee.oauthType, hogee.oauthId)

        assertAll(
            { assertThat(findMember).isNotNull },
            { assertThat(findMember.memberStatus).isEqualTo(MemberStatus.CODE_SURVEY) },
        )
    }
}
