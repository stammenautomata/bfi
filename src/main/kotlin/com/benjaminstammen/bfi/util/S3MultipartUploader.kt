package com.benjaminstammen.bfi.util

import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.amazonaws.services.s3.model.PutObjectResult
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class S3MultipartUploader(val s3Client: AmazonS3) {

    fun upload(file: MultipartFile, bucket: String, name: String): UploadResult {
        val inputStream = file.inputStream

        val metadata = ObjectMetadata()
        metadata.contentLength = inputStream.available().toLong()
        metadata.contentType = file.contentType

        val putObjectRequest = PutObjectRequest(bucket, name, inputStream, metadata)
        val result = s3Client.putObject(putObjectRequest)
        return UploadResult(name, result)
    }

    data class UploadResult(
        val path: String,
        val putObjectResult: PutObjectResult
    )
}