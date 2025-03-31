package codel.config

import codel.auth.TokenProvider
import codel.member.domain.Member
import codel.member.domain.MemberRepository
import codel.member.domain.OauthType
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class TestFixture {
    lateinit var hogee: Member
    lateinit var token: String

    @Autowired
    lateinit var tokenProvider: TokenProvider

    @Autowired
    lateinit var memberRepository: MemberRepository

    @BeforeEach
    fun setUp() {
        hogee =
            Member(
                oauthType = OauthType.APPLE,
                oauthId = "hogee",
            )
        memberRepository.saveMember(hogee)
        token = tokenProvider.provide(hogee)
    }
}
