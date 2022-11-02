package com.benjaminstammen.bfi.model

import java.math.BigDecimal
import java.sql.Date

data class CreateAccountRequest(
        val name: String,
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

data class CreateTransactionRequest(
        val transactionDate: Date,
        val postedDate: Date?,
        val amount: BigDecimal,
        val categoryId: Long,
        val merchantId: Long,
        val merchantDescription: String?,
)