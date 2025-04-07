package codel.member.presentation.swagger

import codel.config.argumentresolver.LoginMember
import codel.member.domain.Member
import codel.member.presentation.request.CodeImageSavedRequest
import codel.member.presentation.request.MemberLoginRequest
import codel.member.presentation.request.ProfileSavedRequest
import codel.member.presentation.response.MemberLoginResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody

@Tag(name = "Member", description = "회원 관련 API")
interface MemberControllerSwagger {
    @Operation(summary = "로그인 및 회원 저장 후 분기 반환", description = "소셜 로그인 정보를 기반으로 회원을 저장하고, JWT 토큰과 회원 분기를 반환합니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "성공적으로 로그인 및 회원 저장됨"),
            ApiResponse(responseCode = "400", description = "요청 값이 잘못됨"),
            ApiResponse(responseCode = "500", description = "서버 내부 오류"),
        ],
    )
    fun loginMember(
        @RequestBody request: MemberLoginRequest,
    ): ResponseEntity<MemberLoginResponse>

    @Operation(summary = "이미지를 제외한 프로필 받기", description = "이미지를 제외한 프로필을 입력받습니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "프로필 성공적으로 저장됨"),
            ApiResponse(responseCode = "400", description = "요청 값이 잘못됨"),
            ApiResponse(responseCode = "500", description = "서버 내부 오류"),
        ],
    )
    fun saveProfile(
        @LoginMember member: Member,
        @RequestBody request: ProfileSavedRequest,
    ): ResponseEntity<Unit>

    @Operation(summary = "코드 프로필 이미지 받기", description = "코드 프로필 이미지 받습니다.")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "코드 프로필 이미지 성공적으로 저장됨"),
            ApiResponse(responseCode = "400", description = "요청 값이 잘못됨"),
            ApiResponse(responseCode = "500", description = "서버 내부 오류"),
        ],
    )
    fun saveCodeImage(
        @LoginMember member: Member,
        @RequestBody request: CodeImageSavedRequest,
    ): ResponseEntity<Unit>
}
