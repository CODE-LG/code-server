package codel.member.domain

class Member(
    val id: Long? = null,
    val oauthType: OauthType,
    val oauthId: String,
    val memberStatus: MemberStatus = MemberStatus.SIGNUP,
) {
    val profile: Profile? = null
}
