package codel.member.infrastructure

import codel.member.infrastructure.entity.MemberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface MemberJpaRepository : JpaRepository<MemberEntity, Long> {
    @Query(
        value = "SELECT * FROM member_entity WHERE oauth_type = :oauthType AND oauth_id = :oauthId",
        nativeQuery = true,
    )
    fun findByOauthTypeAndOauthId(
        @Param("oauthType") oauthType: String,
        @Param("oauthId") oauthId: String,
    ): MemberEntity
}
