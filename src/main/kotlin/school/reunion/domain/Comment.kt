package school.reunion.domain

import java.time.LocalDateTime

class Comment(
        val content: String,
        val createdAt: LocalDateTime,
        val author: Member
) {

    val id: Long = 0
}
