package codel.member.infrastructure

import codel.member.domain.ImageUploader
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import software.amazon.awssdk.core.sync.RequestBody
import software.amazon.awssdk.services.s3.S3Client
import software.amazon.awssdk.services.s3.model.PutObjectRequest
import java.util.UUID

@Component
class S3Uploader(
    private val s3Client: S3Client,
    @Value("\${cloud.aws.s3.bucket}") private val bucket: String,
) : ImageUploader {
    override fun uploadFile(file: MultipartFile): String {
        val fileName = "images/${UUID.randomUUID()}-${file.originalFilename}"

        val putObjectRequest =
            PutObjectRequest
                .builder()
                .bucket(bucket)
                .key(fileName)
                .contentType(file.contentType)
                .build()

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(file.bytes))

        return "https://$bucket.s3.amazonaws.com/$fileName"
    }
}
