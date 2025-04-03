package codel.config

import codel.auth.TokenProvider
import codel.member.domain.Member
import codel.member.domain.MemberRepository
import codel.member.domain.MemberStatus
import codel.member.domain.OauthType
import codel.member.infrastructure.MemberJpaRepository
import codel.member.infrastructure.entity.MemberEntity
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestFixture {
    lateinit var hogee: Member
    lateinit var seokEntity: MemberEntity
    lateinit var token: String

    @Autowired
    lateinit var tokenProvider: TokenProvider

    @Autowired
    lateinit var memberRepository: MemberRepository

    @Autowired
    lateinit var memberJpaRepository: MemberJpaRepository

    @BeforeEach
    fun setUp() {
        memberJpaRepository.deleteAll()
        hogee =
            Member(
                oauthType = OauthType.APPLE,
                oauthId = "hogee",
            )

        memberRepository.loginMember(hogee)

        seokEntity =
            MemberEntity(
                oauthType = OauthType.KAKAO,
                oauthId = "seok",
                memberStatus = MemberStatus.SIGNUP,
            )

        memberJpaRepository.save(seokEntity)

        token = tokenProvider.provide(hogee)
    }
}
