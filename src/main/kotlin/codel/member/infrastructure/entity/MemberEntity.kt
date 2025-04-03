@file:Suppress("ktlint:standard:no-wildcard-imports")

package codel.member.infrastructure.entity

import codel.member.domain.Member
import codel.member.domain.MemberStatus
import codel.member.domain.OauthType
import jakarta.persistence.*

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["oauthType", "oauthId"]),
    ],
)
class MemberEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
    @OneToOne
    private var profileEntity: ProfileEntity? = null,
    private var oauthType: OauthType,
    private var oauthId: String,
    private var memberStatus: MemberStatus,
) {
    companion object {
        fun toEntity(member: Member): MemberEntity =
            MemberEntity(
                oauthType = member.oauthType,
                oauthId = member.oauthId,
                memberStatus = member.memberStatus,
            )
    }

    fun toDomain(): Member =
        Member(
            id = this.id,
            oauthType = this.oauthType,
            oauthId = this.oauthId,
            memberStatus = this.memberStatus,
        )
}
