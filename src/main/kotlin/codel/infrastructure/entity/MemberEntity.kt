package codel.infrastructure.entity

import codel.domain.Member
import codel.domain.OauthType
import jakarta.persistence.*

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(columnNames = ["oauthType", "oauthId"])
    ]
)
class MemberEntity(
        private var oauthType: OauthType,
        private var oauthId: String
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @OneToOne
    var profileEntity: ProfileEntity? = null

    companion object {
        fun toEntity(member: Member): MemberEntity {
            return MemberEntity(
                oauthType = member.oauthType,
                oauthId = member.oauthId
            )
        }
    }
}
