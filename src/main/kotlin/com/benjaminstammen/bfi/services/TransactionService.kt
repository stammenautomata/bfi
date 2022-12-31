package com.benjaminstammen.bfi.services

import com.benjaminstammen.bfi.entities.TransactionEntity
import com.benjaminstammen.bfi.model.*
import com.benjaminstammen.bfi.repositories.TransactionRepository
import org.springframework.stereotype.Service

@Service
class TransactionService(
    val transactionRepository: TransactionRepository,
    val categoryService: CategoryService,
    val merchantService: MerchantService,
) {

    fun saveTransaction(request: TransactionMutableProperties): Transaction {
        enforceConstraints(request)
        return fromEntity(transactionRepository.save(toEntity(request)))
    }

    fun saveAllTransactions(transactions: List<TransactionMutableProperties>): List<Transaction> {
        // TODO: Can consider changing this in the future, but for now it makes sense to validate everything
        //   ahead of time, I think.
        transactions.forEach { enforceConstraints(it) }
        return transactions.map { transactionRepository.save(toEntity(it)) }.map { fromEntity(it) }
    }

    // TODO: paging, ordering
    fun getAllTransactions(): List<Transaction> {
        return transactionRepository.findAll().map { x -> fromEntity(x) }.toList()
    }

    private fun enforceConstraints(mutableProperties: TransactionMutableProperties) {
        merchantService.enforceMerchantExists(mutableProperties.merchantId)
        categoryService.enforceCategoryExists(mutableProperties.categoryId)
    }
}
