package codel.member.domain

class CodeImage(
    val urls: List<String>,
) {
    init {
        require(urls.size in 1..3) { "얼굴 이미지 URL은 1개 이상 3개 이하이어야 합니다." }
    }
}
