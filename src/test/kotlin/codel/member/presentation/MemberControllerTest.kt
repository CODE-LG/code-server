package codel.member.presentation

import codel.config.TestFixture
import codel.member.presentation.response.MemberSavedResponse
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerTest : TestFixture() {
    @LocalServerPort
    var port: Int = 0

    @BeforeEach
    fun setup() {
        RestAssured.port = port
    }

    @DisplayName("Member 중복 로그인 테스트")
    @Test
    fun saveMember() {
        val request =
            mapOf(
                "oauthType" to "APPLE",
                "oauthId" to "hogee",
            )
        val duplicatedUserResponse = MemberSavedResponse(true)

        val token =
            given()
                .contentType(ContentType.JSON)
                .body(request)
                .`when`()
                .post("/v1/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .header("Authorization")
                .removePrefix("Bearer ")

        val response =
            given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer $token")
                .body(request)
                .`when`()
                .post("/v1/auth/login")
                .then()
                .statusCode(200)
                .extract()
                .`as`(MemberSavedResponse::class.java)

        assertEquals(duplicatedUserResponse.isUser, response.isUser)
    }

    @DisplayName("허용된 URL은 인증 없이 접근 가능해야 한다")
    @Test
    fun excludePathTest() {
        val request =
            mapOf(
                "oauthType" to "APPLE",
                "oauthId" to "hogee",
            )

        given()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/v1/auth/login")
            .then()
            .statusCode(200)
    }

    @DisplayName("토큰 없으면 401을 응답해야 한다")
    @Test
    fun unAuthorizeUserTest() {
        given()
            .contentType(ContentType.JSON)
            .`when`()
            .get("/v1/user")
            .then()
            .statusCode(401)
    }

    @DisplayName("로그인 성공 시 accessToken을 발급한다")
    @Test
    fun issueTokenOnLoginSuccess() {
        val request =
            mapOf(
                "oauthType" to "APPLE",
                "oauthId" to "hogee",
            )

        val response =
            given()
                .contentType(ContentType.JSON)
                .body(request)
                .`when`()
                .post("/v1/auth/login")
                .then()
                .statusCode(200)
                .extract()

        val authHeader = response.header("Authorization")
        assertTrue(authHeader != null && authHeader.startsWith("Bearer "))
    }
}
