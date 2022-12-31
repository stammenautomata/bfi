package com.benjaminstammen.bfi.model

import com.benjaminstammen.bfi.entities.TransactionEntity
import com.benjaminstammen.bfi.util.ParsedBankDescription
import java.math.BigDecimal
import java.time.LocalDate

data class Transaction(
    val id: String,
    val transactionDate: LocalDate,
    val postedDate: LocalDate?,
    val amount: BigDecimal,
    val categoryId: String,
    val merchantId: String,
    val bankDescription: String?,
    val bankCategory: String?,
    val invoiceIds: List<String>,
    val note: String?,
    val tags: List<String>
)

data class Item(
    val id: String,
    val transactionId: String,
    val categoryId: String,
    val name: String,
    val unitAmount: BigDecimal,
    val quantity: Int,
    val tags: List<String>
)

data class TransactionImportPartial(
    val transactionDate: LocalDate,
    val postedDate: LocalDate?,
    val bankCategory: String?,
    val bankDescription: String?,
    val bankMemo: String?,
    val amount: BigDecimal,
)


data class TransactionPrefill(
    val transactionDate: LocalDate?,
    val postedDate: LocalDate?,
    val amount: BigDecimal?,
    val categoryId: String?,
    val merchantId: String?,
    val bankDescription: String?,
    val parsedBankDescription: ParsedBankDescription?,
    val bankCategory: String?,
    val invoiceIds: List<String> = emptyList(),
    val note: String? = null,
    val tags: List<String> = emptyList()
)

data class TransactionMutableProperties(
    val transactionDate: LocalDate,
    val postedDate: LocalDate?,
    val amount: BigDecimal,
    val categoryId: String,
    val merchantId: String,
    val merchantDescription: String?,
)

// Supporting Functions

fun toEntity(request: TransactionMutableProperties): TransactionEntity {
    return TransactionEntity(
        transactionDate = request.transactionDate,
        postedDate = request.postedDate,
        amount = request.amount,
        categoryId = request.categoryId,
        merchantId = request.merchantId,
        bankDescription = request.merchantDescription,
        bankCategory = null,
        note = null,
    )
}

fun fromEntity(transaction: TransactionEntity): Transaction {
    return Transaction(
        id = transaction.id!!,
        transactionDate = transaction.transactionDate,
        postedDate = transaction.postedDate,
        amount = transaction.amount,
        categoryId = transaction.categoryId,
        merchantId = transaction.merchantId,
        bankDescription = transaction.bankDescription,
        bankCategory = transaction.bankCategory,
        invoiceIds = transaction.invoiceIds,
        note = transaction.note,
        tags = transaction.tags
    )
}
