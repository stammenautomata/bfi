package com.benjaminstammen.bfi.controllers

import com.benjaminstammen.bfi.model.Transaction
import com.benjaminstammen.bfi.model.TransactionMutableProperties
import com.benjaminstammen.bfi.model.TransactionPartial
import com.benjaminstammen.bfi.services.TransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/transaction")
class TransactionController(
    val transactionService: TransactionService
) {

    // TODO: This is not correct - TransactionPartial should evolve into a more fully-defined object before
    //   being returned to user.
    @PostMapping("/import")
    fun createTransactionsFromCsv(@RequestParam("csv") csvMultipartFile: MultipartFile):
            ResponseEntity<List<TransactionPartial>> {

        return ResponseEntity.ok(emptyList())
    }

    @PostMapping
    fun createTransaction(@RequestBody request: TransactionMutableProperties): ResponseEntity<Transaction> {
        val transaction = transactionService.saveTransaction(request)
        return ResponseEntity.ok(transaction)
    }

    @GetMapping
    fun listTransactions(): ResponseEntity<List<Transaction>> {
        return ResponseEntity.ok(transactionService.getAllTransactions())
    }
}
