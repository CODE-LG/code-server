package codel.member.presentation

import codel.member.presentation.response.MemberSavedResponse
import io.restassured.RestAssured
import io.restassured.RestAssured.given
import io.restassured.http.ContentType
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MemberControllerTest {
    @LocalServerPort
    var port: Int = 0

    @BeforeEach
    fun setup() {
        RestAssured.port = port
    }

    @DisplayName("Member 테스트")
    @Test
    fun saveMember() {
        val request =
            mapOf(
                "oauthType" to "APPLE",
                "oauthId" to "hogee",
            )
        val newUserResponse = MemberSavedResponse(false)

        given()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/v1/auth/login")
            .then()
            .statusCode(200)
            .extract()
            .`as`(MemberSavedResponse::class.java)
            .also { response ->
                assertEquals(newUserResponse.isUser, response.isUser)
            }

        val duplicatedUserResponse = MemberSavedResponse(true)

        given()
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/v1/auth/login")
            .then()
            .statusCode(200)
            .extract()
            .`as`(MemberSavedResponse::class.java)
            .also { response ->
                assertEquals(duplicatedUserResponse.isUser, response.isUser)
            }
    }
}
