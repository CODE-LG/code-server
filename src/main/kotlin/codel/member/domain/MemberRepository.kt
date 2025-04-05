package codel.member.domain

import codel.member.infrastructure.MemberJpaRepository
import codel.member.infrastructure.ProfileJpaRepository
import codel.member.infrastructure.entity.MemberEntity
import codel.member.infrastructure.entity.ProfileEntity
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class MemberRepository(
    private val memberJpaRepository: MemberJpaRepository,
    private val profileJpaRepository: ProfileJpaRepository,
) {
    fun loginMember(member: Member): Member =
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

    fun saveProfile(
        member: Member,
        profile: Profile,
    ) {
        val memberEntity = findMemberEntity(member)
        val profileEntity = ProfileEntity.toEntity(profile)

        profileJpaRepository.save(profileEntity)
        memberEntity.saveProfileEntity(profileEntity)
    }

    fun changeMemberStatus(
        member: Member,
        status: MemberStatus,
    ) {
        val memberEntity = findMemberEntity(member)

        memberEntity.changeMemberStatus(status)
    }

    private fun findMemberEntity(member: Member): MemberEntity {
        val memberId = member.id ?: throw IllegalArgumentException("member id가 비어있습니다.")

        return memberJpaRepository.findByIdOrNull(memberId) ?: throw IllegalArgumentException("멤버가 존재하지 않습니다.")
    }

    fun saveImagePath(
        member: Member,
        codeImage: CodeImage,
    ) {
        member.saveCodeImage(codeImage)

        val memberEntity = MemberEntity.toEntity(member)
    }
}
