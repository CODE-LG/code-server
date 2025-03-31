package codel.member.infrastructure

import codel.member.domain.OauthType
import codel.member.infrastructure.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MemberJpaRepository : JpaRepository<MemberEntity, Long> {
    fun findByOauthTypeAndOauthId(
        oauthType: OauthType,
        oauthId: String,
    ): MemberEntity
}
