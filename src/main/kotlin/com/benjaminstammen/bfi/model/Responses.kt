package com.benjaminstammen.bfi.model

import java.math.BigDecimal
import java.sql.Date

data class Account(
    val id: String,
    val name: String,
    val autoTags: List<String>,
    val note: String?
)

data class Transaction(
    val id: String,
    val transactionDate: Date,
    val postedDate: Date?,
    val amount: BigDecimal,
    val categoryId: String,
    val merchantId: String,
    val bankDescription: String?,
    val bankCategory: String?,
    val invoices: List<Invoice>,
    val items: List<Item>,
    val note: String?,
    val tags: List<String>
)

data class Item(
    val id: String,
    val categoryId: String,
    val name: String,
    val unitAmount: BigDecimal,
    val quantity: Int,
    val tags: List<String>
) {}

data class Invoice(
    val id: String,
    val resourceUri: String,
    val md5: String
)
