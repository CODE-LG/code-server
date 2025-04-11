package codel.member.domain

import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CodeImageTest {
    @DisplayName("페이스 이미지는 3장이여야 한다.")
    @Test
    fun initTest() {
        assertAll(
            {
                assertDoesNotThrow {
                    CodeImage(listOf("url1", "url2", "url3"))
                }
            },
            {
                assertThrows(IllegalArgumentException::class.java) {
                    CodeImage(listOf())
                }
            },
            {
                assertThrows(IllegalArgumentException::class.java) {
                    CodeImage(listOf("url1", "url2", "url3", "url4"))
                }
            },
        )
    }
}
