package codel.member.business

import codel.config.TestFixture
import codel.member.domain.ImageUploader
import codel.member.domain.MemberRepository
import codel.member.domain.MemberStatus
import codel.member.domain.OauthType
import codel.member.presentation.request.MemberLoginRequest
import codel.member.presentation.request.ProfileSavedRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.mock.web.MockMultipartFile

@SpringBootTest
class MemberServiceTest(
    @Autowired
    private val memberService: MemberService,
    @Autowired
    private val memberRepository: MemberRepository,
) : TestFixture() {
    @DisplayName("첫 로그인을 한 멤버의 상태는 SignUp 이다.")
    @Test
    fun loginMemberSuccessTest() {
        val memberLoginRequest =
            MemberLoginRequest(
                oauthType = OauthType.KAKAO,
                oauthId = "hogee",
            )

        val memberStatus = memberService.loginMember(memberLoginRequest).memberStatus

        assertThat(memberStatus).isEqualTo(MemberStatus.SIGNUP)
    }

    @DisplayName("프로필을 저장에 성공한 후 멤버 상태는 CODE_SURVEY 이다.")
    @Test
    fun saveProfileSuccessTest() {
        val profileSavedRequest =
            ProfileSavedRequest(
                codeName = "seok",
                age = 28,
                job = "백엔드 개발자",
                alcohol = "자주 마심",
                smoke = "비흡연자 - 흡연자와 교류 NO",
                hobby = listOf("영화 & 드라마", "여행 & 캠핑"),
                style = listOf("표현을 잘하는 직진형", "상대가 필요할 때 항상 먼저 연락하는 스타일"),
                bigCity = "경기도",
                smallCity = "성남시",
                mbti = "isfj",
                introduce = "잘부탁드립니다!",
            )
        memberService.saveProfile(memberSignup, profileSavedRequest)

        val findMember =
            memberService.findMember(
                oauthType = memberSignup.oauthType,
                oauthId = memberSignup.oauthId,
            )

        Assertions.assertAll(
            { assertThat(findMember.profile).isNotNull },
            { assertThat(findMember.memberStatus).isEqualTo(MemberStatus.CODE_SURVEY) },
        )
    }

    @DisplayName("saveCodeImage는 S3에 업로드하고 저장소에 이미지 경로 저장한다")
    @Test
    fun saveCodeImageTest() {
        val imageUploader = mock(ImageUploader::class.java)
        val mockMemberService = MemberService(memberRepository, imageUploader)
        val member = memberCodeSurvey

        val file1 = MockMultipartFile("image1", "image1.jpg", "image/jpeg", byteArrayOf(1, 2, 3))
        val file2 = MockMultipartFile("image2", "image2.jpg", "image/jpeg", byteArrayOf(4, 5, 6))
        val request = listOf(file1, file2)

        `when`(imageUploader.uploadFile(file1)).thenReturn("https://s3.amazonaws.com/image1.jpg")
        `when`(imageUploader.uploadFile(file2)).thenReturn("https://s3.amazonaws.com/image2.jpg")

        mockMemberService.saveCodeImage(member, request)

        verify(imageUploader).uploadFile(file1)
        verify(imageUploader).uploadFile(file2)
        val memberEntity = memberJpaRepository.findById(member.id!!).get()
        val savedMember = memberEntity.toDomain()
        assertThat(savedMember.codeImage).isNotNull
        assertThat(savedMember.memberStatus).isEqualTo(MemberStatus.CODE_PROFILE_IMAGE)
    }
}
