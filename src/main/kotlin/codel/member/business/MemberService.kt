package codel.member.business

import codel.member.domain.CodeImage
import codel.member.domain.Member
import codel.member.domain.MemberRepository
import codel.member.domain.Profile
import codel.member.domain.S3Uploader
import codel.member.presentation.request.CodeImageSavedRequest
import codel.member.presentation.request.MemberLoginRequest
import codel.member.presentation.request.ProfileSavedRequest
import codel.member.presentation.response.MemberLoginResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val s3Uploader: S3Uploader,
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
    fun saveCodeImage(request: CodeImageSavedRequest) {
        val imageFiles = request.imageFiles
        val codeImages = CodeImage(imageFiles.map { file -> s3Uploader.uploadFile(file) })

        memberRepository.saveImagePath(codeImages)

        // memberStatus 수정
    }
}
