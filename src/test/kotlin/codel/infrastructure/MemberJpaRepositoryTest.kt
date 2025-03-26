package codel.infrastructure

import codel.domain.OauthType
import codel.infrastructure.entity.MemberEntity
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException

@SpringBootTest
class MemberJpaRepositoryTest (
        @Autowired
        private val memberJpaRepository: MemberJpaRepository
){

    @DisplayName("중복된 멤버에 대해 유니크 제약 조건을 발생시킨다.")
    @Test
    fun saveMemberTest() {
        val memberEntity1 = MemberEntity(OauthType.APPLE, "hogee")
        val memberEntity2 = MemberEntity(OauthType.APPLE, "hogee")
        memberJpaRepository.save(memberEntity1)

        Assertions.assertThrows(DataIntegrityViolationException::class.java) {
            memberJpaRepository.save(memberEntity2)
        }
    }
}
