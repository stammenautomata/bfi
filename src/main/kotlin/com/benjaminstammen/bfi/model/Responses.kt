package com.benjaminstammen.bfi.model

import java.math.BigDecimal
import java.sql.Date

data class Account(
        val id: Long,
        val name: String,
        val note: String?,
)

data class Category(
        val id: Long,
        val name: String,
        val note: String?,
)

data class Transaction(
        val id: Long,
        val transactionDate: Date,
        val postedDate: Date?,
        val amount: BigDecimal,
        val category: Category,
        val merchant: Merchant,
        val merchantDescription: String?,
)

data class Merchant(
        val id: Long,
        val name: String,
        val note: String?,
)

data class MerchantDescription(
        val description: String,
        val merchant: Merchant,
)