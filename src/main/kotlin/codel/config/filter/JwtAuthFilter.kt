package codel.config.filter

import codel.auth.TokenProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthFilter(
    private val tokenProvider: TokenProvider,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        if (request.requestURI == "/v1/auth/login") {
            filterChain.doFilter(request, response)
            return
        }

        val token = resolveToken(request)

        if (token == null || !tokenProvider.validateToken(token)) {
            response.status = HttpServletResponse.SC_UNAUTHORIZED
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.writer.write("""{"message": "인증되지 않은 사용자입니다."}""")
            return
        }
        val memberId = tokenProvider.extractMemberId(token)
        request.setAttribute("memberId", memberId)

        filterChain.doFilter(request, response)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearer = request.getHeader("Authorization")
        return if (bearer != null && bearer.startsWith("Bearer ")) {
            bearer.substring(7)
        } else {
            null
        }
    }
}
