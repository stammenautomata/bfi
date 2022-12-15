package com.benjaminstammen.bfi.controllers

import com.benjaminstammen.bfi.model.Transaction
import com.benjaminstammen.bfi.model.TransactionMutableProperties
import com.benjaminstammen.bfi.services.TransactionService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/transaction")
class TransactionController(
    val transactionService: TransactionService
) {

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
