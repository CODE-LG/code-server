package codel.member.domain

import codel.member.infrastructure.MemberJpaRepository
import codel.member.infrastructure.entity.MemberEntity
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Component

@Component
class MemberRepository(
    private val memberJpaRepository: MemberJpaRepository,
) {
    fun saveMember(member: Member): Boolean =
        try {
            memberJpaRepository.save(MemberEntity.toEntity(member))
            false
        } catch (e: DataIntegrityViolationException) {
            true
        }
}
