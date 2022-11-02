package com.benjaminstammen.bfi.router

import com.benjaminstammen.bfi.model.CreateTransactionRequest
import com.benjaminstammen.bfi.model.Transaction
import com.benjaminstammen.bfi.repositories.CategoryRepository
import com.benjaminstammen.bfi.repositories.MerchantRepository
import com.benjaminstammen.bfi.repositories.TransactionRepository
import com.benjaminstammen.bfi.translation.fromEntity
import com.benjaminstammen.bfi.translation.toEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/transaction")
class TransactionRouter(
        val transactionRepository: TransactionRepository,
        val categoryRepository: CategoryRepository,
        val merchantRepository: MerchantRepository) {

    @PostMapping
    fun createTransaction(@RequestBody request: CreateTransactionRequest): ResponseEntity<Transaction> {
        val maybeCategory = categoryRepository.findById(request.categoryId)
        if (!maybeCategory.isPresent) {
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Category with ID '${request.categoryId}' does not exist."
            )
        }

        val maybeMerchant = merchantRepository.findById(request.merchantId)
        if (!maybeMerchant.isPresent) {
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Merchant with ID '${request.merchantId}' does not exist."
            )
        }

        val persistedEntity = transactionRepository.save(toEntity(
                request, maybeCategory.get(), maybeMerchant.get()
        ))

        return ResponseEntity.ok(fromEntity(persistedEntity))
    }

    @GetMapping
    fun listTransactions() : ResponseEntity<List<Transaction>> {
        val transactions = ArrayList<Transaction>()
        transactionRepository.findAll().mapTo(transactions) { fromEntity(it) }
        return ResponseEntity.ok(transactions)
    }
}