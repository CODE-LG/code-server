package codel.member.domain

class Member(
    val id: Long? = null,
    val profile: Profile? = null,
    val oauthType: OauthType,
    val oauthId: String,
    val memberStatus: MemberStatus = MemberStatus.SIGNUP,
)
