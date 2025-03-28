package codel.member.domain

class Member(
    val oauthType: OauthType,
    val oauthId: String,
) {
    val id: Long = 0
    val profile: Profile? = null
}
