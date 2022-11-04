package com.benjaminstammen.bfi.router

import com.benjaminstammen.bfi.entities.ReceiptEntity
import com.benjaminstammen.bfi.model.CreateTransactionStubRequest
import com.benjaminstammen.bfi.model.TransactionStub
import com.benjaminstammen.bfi.repositories.ReceiptRepository
import com.benjaminstammen.bfi.repositories.TransactionStubRepository
import com.benjaminstammen.bfi.translation.fromEntity
import com.benjaminstammen.bfi.translation.toEntity
import com.benjaminstammen.bfi.util.ReceiptUploader
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/transaction_stub")
class TransactionStubRouter(
    val transactionStubRepository: TransactionStubRepository,
    val receiptRepository: ReceiptRepository,
    val receiptUploader: ReceiptUploader,
    @Value("\${bfi.s3.bucket}") val bfiBucket: String,
    @Value("\${bfi.s3.receipt-prefix}") val s3ReceiptPrefix: String
) {

    /**
     * Swagger doesn't support JSON properly in multipart requests, so instead of
     * providing JSON in a nice textbox, you have to upload a JSON file:
     * https://github.com/springdoc/springdoc-openapi/issues/820#issuecomment-672875450
     */
    @PostMapping(consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createStubTransaction(
        @RequestPart @Parameter(
            schema = Schema(
                type = "string",
                format = "binary"
            )
        )request: CreateTransactionStubRequest,
        @RequestPart image: MultipartFile
    ): ResponseEntity<TransactionStub> {
        val uploadResult = receiptUploader.uploadReceipt(image)

        val receiptEntity = ReceiptEntity()
        receiptEntity.imagePath = uploadResult.path
        receiptEntity.imageMd5 = uploadResult.putObjectResult.contentMd5

        val transactionStubEntity = toEntity(request, receiptEntity)
        val persistedEntity = transactionStubRepository.save(transactionStubEntity)

        // TODO: will have to generate a presigned URL or something.
        //  maybe obfuscated public is OK, too. We'll see.
        return ResponseEntity.ok(fromEntity(persistedEntity))
}

    @GetMapping
    fun listTransactions(): ResponseEntity<List<TransactionStub>> {
        val transactions = ArrayList<TransactionStub>()
        transactionStubRepository.findAll().mapTo(transactions) { fromEntity(it) }
        return ResponseEntity.ok(transactions)
    }
}