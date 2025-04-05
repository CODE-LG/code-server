package codel.config.argumentresolver

import codel.auth.TokenProvider
import codel.auth.exception.AuthException
import codel.member.business.MemberService
import codel.member.domain.Member
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class MemberArgumentResolver(
    private val memberService: MemberService,
    private val tokenProvider: TokenProvider,
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.hasParameterAnnotation(LoginMember::class.java) &&
            parameter.parameterType == Member::class.java

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: org.springframework.web.bind.support.WebDataBinderFactory?,
    ): Any? {
        val token = resolveToken(webRequest)
        val memberId = tokenProvider.extractMemberId(token)

        return memberService.findMember(memberId)
    }

    private fun resolveToken(webRequest: NativeWebRequest): String {
        val bearer = webRequest.getHeader("Authorization")
        return if (bearer != null && bearer.startsWith("Bearer ")) {
            bearer.substring(7)
        } else {
            throw AuthException(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다.")
        }
    }
}
