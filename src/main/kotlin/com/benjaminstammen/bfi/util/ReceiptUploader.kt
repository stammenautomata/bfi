package com.benjaminstammen.bfi.util

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Component
class ReceiptUploader(val multipartUploader: S3MultipartUploader,
                      @Value("\${bfi.s3.bucket}") val bfiBucket: String,
                      @Value("\${bfi.s3.receipt-prefix}") val s3ReceiptPrefix: String) {

    fun uploadReceipt(file: MultipartFile): S3MultipartUploader.UploadResult {
        // TODO: Maybe perform some header validation in the future.
        val receiptPath = "${s3ReceiptPrefix}/${UUID.randomUUID()}"
        return multipartUploader.upload(file, bfiBucket, receiptPath)
    }
}