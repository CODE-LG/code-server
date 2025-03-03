package school.reunion.domain

import java.time.LocalDateTime

class Post (
        val title: String,
        val content: String,
        val createdAt: LocalDateTime,
        val comments: List<Comment>,
        val author: Member
) {

    val id: Long = 0
}
