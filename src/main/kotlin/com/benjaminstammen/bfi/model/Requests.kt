package com.benjaminstammen.bfi.model

import java.math.BigDecimal
import java.sql.Date

data class AccountMutableProperties(
        val name: String,
        val autoTags: List<String> = ArrayList(),
        val note: String?,
)

data class CreateCategoryRequest(
        val name: String,
        val note: String?,
)

data class CreateMerchantRequest(
        val name: String,
        val note: String?,
)

data class CreateMerchantDescriptionRequest (
        val description: String,
        val merchantId: Long,
)

data class CreateTransactionStubRequest(
        val amount: BigDecimal,
        val note: String?,
)
data class TransactionMutableProperties(
        val transactionDate: Date,
        val postedDate: Date?,
        val amount: BigDecimal,
        val categoryId: String,
        val merchantId: String,
        val merchantDescription: String?,
)
