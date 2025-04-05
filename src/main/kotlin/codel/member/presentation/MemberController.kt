package codel.member.presentation

import codel.auth.business.AuthService
import codel.config.argumentresolver.LoginMember
import codel.member.business.MemberService
import codel.member.domain.Member
import codel.member.presentation.request.CodeImageSavedRequest
import codel.member.presentation.request.MemberLoginRequest
import codel.member.presentation.request.ProfileSavedRequest
import codel.member.presentation.response.MemberLoginResponse
import codel.member.presentation.swagger.MemberControllerSwagger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService,
    private val authService: AuthService,
) : MemberControllerSwagger {
    @PostMapping("/v1/member/login")
    override fun loginMember(
        @RequestBody request: MemberLoginRequest,
    ): ResponseEntity<MemberLoginResponse> {
        val memberSavedResponse = memberService.loginMember(request)
        val token = authService.provideToken(request)
        return ResponseEntity
            .ok()
            .header("Authorization", "Bearer $token")
            .body(memberSavedResponse)
    }

    @PostMapping("/v1/member/profile")
    override fun saveProfile(
        @LoginMember member: Member,
        @RequestBody request: ProfileSavedRequest,
    ): ResponseEntity<Unit> {
        memberService.saveProfile(member, request)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/v1/member/codeimage")
    override fun saveCodeImage(
        @LoginMember member: Member,
        @RequestBody request: CodeImageSavedRequest,
    ): ResponseEntity<Unit> {
        // 이미지를 엔티티로 저장
        // s3 통신 방식 변경 필요
        memberService.saveCodeImage(member, request)
        return ResponseEntity.ok().build()
    }
}
