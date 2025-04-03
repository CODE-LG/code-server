package codel.member.infrastructure.entity

import codel.member.domain.Profile
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private var id: Long? = null,
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
    private var codeImage: String? = null, // 복수
    private var faceImage: String? = null, // 복수
) {
    companion object {
        fun toEntity(profile: Profile): ProfileEntity =
            ProfileEntity(
                codeName = profile.codeName,
                age = profile.age,
                job = profile.job,
                alcohol = profile.alcohol,
                smoke = profile.smoke,
                hobby = serializeAttribute(profile.hobby),
                style = serializeAttribute(profile.style),
                bigCity = profile.bigCity,
                smallCity = profile.smallCity,
                mbti = profile.mbti,
                introduce = profile.introduce,
            )

        private fun serializeAttribute(attribute: List<String>): String = attribute.joinToString(separator = ",")
    }

    fun updateCodeImage(codeImages: List<String>) {
        codeImage = serializeAttribute(codeImages)
    }

    fun updateFaceImage(faceImages: List<String>) {
        faceImage = serializeAttribute(faceImages)
    }
}
