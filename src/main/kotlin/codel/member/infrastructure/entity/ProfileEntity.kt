package codel.member.infrastructure.entity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ProfileEntity(
    private var codeName: String,
    private var age: Int,
    private var job: String,
    private var alcohol: String,
    private var smoke: String,
    private var hobby: String, // 복수
    private var style: String, // 복수
    private var bigCity: String,
    private var smallCity: String,
    private var mbti: String,
    private var introduce: String,
    private var codeImage: String, // 복수
    private var faceImage: String, // 복수
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}
