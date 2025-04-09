package codel.member.domain

class Member(
    val id: Long? = null,
    val profile: Profile? = null,
    val oauthType: OauthType,
    val oauthId: String,
    val codeImage: CodeImage? = null,
    val faceImage: FaceImage? = null,
    val memberStatus: MemberStatus = MemberStatus.SIGNUP,
)
