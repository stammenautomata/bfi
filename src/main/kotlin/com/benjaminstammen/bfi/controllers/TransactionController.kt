package com.benjaminstammen.bfi.controllers

import com.benjaminstammen.bfi.model.Transaction
import com.benjaminstammen.bfi.model.TransactionMutableProperties
import com.benjaminstammen.bfi.model.TransactionPrefill
import com.benjaminstammen.bfi.services.TransactionGeneratorService
import com.benjaminstammen.bfi.services.TransactionService
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/transactions")
class TransactionController(
    val transactionService: TransactionService,
    val transactionGeneratorService: TransactionGeneratorService
) {
    @PostMapping
    fun createTransaction(@RequestBody request: TransactionMutableProperties): ResponseEntity<Transaction> {
        val transaction = transactionService.saveTransaction(request)
        return ResponseEntity.ok(transaction)
    }

    @PostMapping("/bulk_create")
    fun bulkCreateTransaction(@RequestBody request: List<TransactionMutableProperties>): ResponseEntity<List<Transaction>> {
        val transactions = transactionService.saveAllTransactions(request)
        return ResponseEntity.ok(transactions)
    }

    @GetMapping
    fun listTransactions(): ResponseEntity<List<Transaction>> {
        return ResponseEntity.ok(transactionService.getAllTransactions())
    }

    /* Imports */

    @PostMapping("/prefill_from_csv", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun createTransactionsFromCsv(@RequestPart("file") csvMultipartFile: MultipartFile):
            ResponseEntity<List<TransactionPrefill>> {
        return ResponseEntity.ok(
            transactionGeneratorService.generateTransactionsFromCsv(
                csvMultipartFile.inputStream
            )
        )
    }
}
