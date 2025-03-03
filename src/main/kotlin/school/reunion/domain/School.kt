package school.reunion.domain

import java.time.Year

class School (
        val name: String,
        val graduationYear: Int,
        val members: List<Member>,
        val posts: List<Post>
) {

    val id: Long = 0
}
