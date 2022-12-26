package com.benjaminstammen.bfi.services

import com.benjaminstammen.bfi.entities.ItemEntity
import com.benjaminstammen.bfi.entities.TransactionEntity
import com.benjaminstammen.bfi.model.*
import com.benjaminstammen.bfi.repositories.InvoiceRepository
import com.benjaminstammen.bfi.repositories.ItemRepository
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

        return fromEntity(
            transactionRepository.save(
                TransactionEntity(
                    transactionDate = request.transactionDate,
                    postedDate = request.postedDate,
                    amount = request.amount,
                    categoryId = request.categoryId,
                    merchantId = request.merchantId,
                    bankDescription = request.merchantDescription,
                    bankCategory = null,
                    note = null,
                )
            )
        )
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
