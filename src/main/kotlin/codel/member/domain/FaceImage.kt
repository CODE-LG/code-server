package codel.member.domain

class FaceImage(
    val urls: List<String>,
) {
    init {
        require(urls.size == 3) { "얼굴 이미지 URL은 정확히 3개여야 합니다." }
    }
}
