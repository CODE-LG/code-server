package codel.member.domain

import codel.member.infrastructure.MemberJpaRepository
import codel.member.infrastructure.entity.MemberEntity
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component

@Component
class MemberRepository(
    private val memberJpaRepository: MemberJpaRepository,
) {
    fun saveMember(member: Member): Member =
        try {
            val memberEntity = memberJpaRepository.save(MemberEntity.toEntity(member))
            memberEntity.toDomain()
        } catch (e: DataIntegrityViolationException) {
            val memberEntity = memberJpaRepository.findByOauthTypeAndOauthId(member.oauthType, member.oauthId)
            memberEntity.toDomain()
        }

    fun findMember(
        oauthType: OauthType,
        oauthId: String,
    ): Member {
        val memberEntity = memberJpaRepository.findByOauthTypeAndOauthId(oauthType, oauthId)
        return memberEntity.toDomain()
    }
}
