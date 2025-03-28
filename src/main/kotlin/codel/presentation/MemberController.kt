package codel.presentation

import codel.business.MemberService
import codel.presentation.request.MemberSavedRequest
import codel.presentation.response.MemberSavedResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MemberController(
    private val memberService: MemberService,
) {
    @PostMapping("/v1/auth/login")
    fun saveMember(
        @RequestBody request: MemberSavedRequest,
    ): ResponseEntity<MemberSavedResponse> {
        val memberSavedResponse = memberService.saveMember(request)

        return ResponseEntity.ok(memberSavedResponse)
    }
}
