package codel.auth

import codel.config.TestFixture
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class TokenProviderTest : TestFixture() {
    @DisplayName("토큰 정상 생성 테스트")
    @Test
    fun provideTest() {
        val token = tokenProvider.provide(hogee)

        Assertions.assertThat(token).isNotNull()
    }

    @DisplayName("토큰 유효성 검증 테스트")
    @Test
    fun validateTokenTest() {
        val isToken = tokenProvider.validateToken("hogee")

        Assertions.assertThat(isToken).isFalse()
    }
}
