package codel.config

import codel.auth.TokenProvider
import codel.member.domain.Member
import codel.member.domain.MemberRepository
import codel.member.domain.MemberStatus
import codel.member.domain.OauthType
import codel.member.domain.Profile
import codel.member.infrastructure.MemberJpaRepository
import codel.member.infrastructure.ProfileJpaRepository
import codel.member.infrastructure.entity.MemberEntity
import codel.member.infrastructure.entity.ProfileEntity
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestFixture {
    lateinit var member: Member
    lateinit var nonSavedMember: Member
    lateinit var memberEntity: MemberEntity
    lateinit var token: String
    lateinit var nonSavedProfile: Profile
    lateinit var profile: Profile

    @Autowired
    lateinit var tokenProvider: TokenProvider

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var profileJpaRepository: ProfileJpaRepository

    @Autowired
    lateinit var memberJpaRepository: MemberJpaRepository

    @BeforeEach
    fun setUp() {
        memberJpaRepository.deleteAll()
        member =
            Member(
                oauthType = OauthType.APPLE,
                oauthId = "hogee",
            )
        nonSavedMember =
            Member(
                oauthType = OauthType.APPLE,
                oauthId = "seok",
            )
        memberEntity =
            MemberEntity(
                oauthType = OauthType.KAKAO,
                oauthId = "seok",
                memberStatus = MemberStatus.SIGNUP,
            )
        nonSavedProfile =
            Profile(
                codeName = "hogee",
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
        profile =
            Profile(
                codeName = "hogee",
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

        member = memberRepository.loginMember(member)
        memberJpaRepository.save(memberEntity)
        profileJpaRepository.save(ProfileEntity.toEntity(profile))
        token = tokenProvider.provide(member)
    }
}
