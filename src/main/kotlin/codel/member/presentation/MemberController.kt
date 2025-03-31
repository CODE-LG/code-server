package codel.member.presentation

import codel.auth.business.AuthService
import codel.member.business.MemberService
import codel.member.presentation.request.MemberSavedRequest
import codel.member.presentation.response.MemberSavedResponse
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
    @PostMapping("/v1/auth/login")
    override fun saveMember(
        @RequestBody request: MemberSavedRequest,
    ): ResponseEntity<MemberSavedResponse> {
        val memberSavedResponse = memberService.saveMember(request)
        val token = authService.provideToken(request)
        return ResponseEntity
            .ok()
            .header("Authorization", "Bearer $token")
            .body(memberSavedResponse)
    }
}
