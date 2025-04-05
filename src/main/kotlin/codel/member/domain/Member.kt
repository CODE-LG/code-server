package codel.member.domain

class Member(
    val id: Long? = null,
    val profile: Profile? = null,
    val oauthType: OauthType,
    val oauthId: String,
    private var codeImage: CodeImage? = null,
    private var faceImage: FaceImage? = null,
    val memberStatus: MemberStatus = MemberStatus.SIGNUP,
) {
    fun saveCodeImage(codeImage: CodeImage) {
        this.codeImage = codeImage
    }

    fun saveFaceImage(faceImage: FaceImage) {
        this.faceImage = faceImage
    }
}
