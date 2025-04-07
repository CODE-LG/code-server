package codel.member.infrastructure.entity

import codel.member.domain.Member
import codel.member.domain.MemberStatus
import codel.member.domain.OauthType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

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
            profile = this.profileEntity?.toDomain(),
            oauthType = this.oauthType,
            oauthId = this.oauthId,
            memberStatus = this.memberStatus,
        )

    fun saveProfileEntity(profileEntity: ProfileEntity) {
        this.profileEntity = profileEntity
    }

    fun changeMemberStatus(status: MemberStatus) {
        this.memberStatus = status
    }
}
