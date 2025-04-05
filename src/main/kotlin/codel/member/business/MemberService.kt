package codel.member.business

import codel.member.domain.CodeImage
import codel.member.domain.ImageUploader
import codel.member.domain.Member
import codel.member.domain.MemberRepository
import codel.member.domain.Profile
import codel.member.presentation.request.CodeImageSavedRequest
import codel.member.presentation.request.MemberLoginRequest
import codel.member.presentation.request.ProfileSavedRequest
import codel.member.presentation.response.MemberLoginResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val imageUploader: ImageUploader,
) {
    fun loginMember(request: MemberLoginRequest): MemberLoginResponse {
        val member =
            Member(
                oauthType = request.oauthType,
                oauthId = request.oauthId,
            )
        val loginMember = memberRepository.loginMember(member)

        return MemberLoginResponse(loginMember.memberStatus)
    }

    fun saveProfile(request: ProfileSavedRequest) {
        val profile =
            Profile(
                codeName = request.codeName,
                age = request.age,
                job = request.job,
                alcohol = request.alcohol,
                smoke = request.smoke,
                hobby = request.hobby,
                style = request.style,
                bigCity = request.bigCity,
                smallCity = request.smallCity,
                mbti = request.mbti,
                introduce = request.introduce,
            )
        memberRepository.saveProfile(profile)
    }

    @Transactional
    fun saveCodeImage(
        member: Member,
        request: CodeImageSavedRequest,
    ) {
        val codeImage = uploadFile(request)

        memberRepository.saveImagePath(member, codeImage)

        // memberStatus 수정
    }

    private fun uploadFile(request: CodeImageSavedRequest): CodeImage {
        val imageFiles = request.imageFiles
        return CodeImage(imageFiles.map { file -> imageUploader.uploadFile(file) })
    }
}
