package school.reunion.domain

class Member(
        val name: String,
        val schools: List<School>
) {

    val id: Long = 0
}
