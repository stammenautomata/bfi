package com.benjaminstammen.bfi.services

import com.benjaminstammen.bfi.entities.ItemEntity
import com.benjaminstammen.bfi.entities.TransactionEntity
import com.benjaminstammen.bfi.model.Invoice
import com.benjaminstammen.bfi.model.Item
import com.benjaminstammen.bfi.model.Transaction
import com.benjaminstammen.bfi.model.TransactionMutableProperties
import com.benjaminstammen.bfi.repositories.CategoryRepository
import com.benjaminstammen.bfi.repositories.InvoiceRepository
import com.benjaminstammen.bfi.repositories.ItemRepository
import com.benjaminstammen.bfi.repositories.MerchantRepository
import com.benjaminstammen.bfi.repositories.TransactionRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class TransactionService(
    val transactionRepository: TransactionRepository,
    val categoryRepository: CategoryRepository,
    val merchantRepository: MerchantRepository,
    val itemRepository: ItemRepository,
    val invoiceRepository: InvoiceRepository
) {

    fun saveTransaction(request: TransactionMutableProperties): Transaction {
        val maybeCategory = categoryRepository.findById(request.categoryId)
        if (!maybeCategory.isPresent) {
            throw CategoryNotFoundException(request.categoryId)
        }

        val maybeMerchant = merchantRepository.findById(request.merchantId)
        if (!maybeMerchant.isPresent) {
            throw MerchantNotFoundException(request.merchantId)
        }

        return transactionFromEntity(
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
        return transactionRepository.findAll().map { x ->
            val items = itemRepository.findAllByTransactionId(x.id!!)
                .map { y: ItemEntity ->
                    Item(
                        id = y.id!!,
                        categoryId = y.categoryId,
                        name = y.name,
                        unitAmount = y.unitAmount,
                        quantity = y.quantity,
                        tags = y.tags
                    )
                }
            val invoices = invoiceRepository.findAllById(x.invoiceIds)
                .map { y ->
                    Invoice(
                        id = y.id!!,
                        resourceUri = y.resourceUri,
                        md5 = y.md5
                    )
                }
            transactionFromEntity(
                entity = x,
                invoices = invoices,
                items = items)
        }
    }

    fun transactionFromEntity(entity: TransactionEntity): Transaction {
        return transactionFromEntity(
            entity = entity,
            invoices = emptyList(),
            items = emptyList()
        )
    }

    fun transactionFromEntity(
        entity: TransactionEntity,
        invoices: List<Invoice>,
        items: List<Item>
    ): Transaction {
        return Transaction(
            id = entity.id!!,
            transactionDate = entity.transactionDate,
            postedDate = entity.postedDate,
            amount = entity.amount,
            categoryId = entity.categoryId,
            merchantId = entity.merchantId,
            bankDescription = entity.bankDescription,
            bankCategory = entity.bankCategory,
            invoices = invoices,
            items = items,
            note = entity.note,
            tags = entity.tags
        )
    }
}
