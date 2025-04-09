package codel.config.argumentresolver

import codel.member.business.MemberService
import codel.member.domain.Member
import codel.member.domain.OauthType
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class MemberArgumentResolver(
    private val memberService: MemberService,
) : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.hasParameterAnnotation(LoginMember::class.java) &&
            parameter.parameterType == Member::class.java

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?,
    ): Any? {
        val httpServletRequest =
            webRequest.getNativeRequest(HttpServletRequest::class.java)
                ?: throw IllegalStateException("HttpServletRequest를 가져올 수 없습니다.")

        val oauthId =
            httpServletRequest.getAttribute("oauthId") as? String
                ?: throw IllegalArgumentException("oauthId가 요청이 없습니다.")
        val oauthType =
            httpServletRequest.getAttribute("oauthType") as? OauthType
                ?: throw IllegalArgumentException("oauthType이 요청이 없습니다.")

        return memberService.findMember(oauthType, oauthId)
    }
}
