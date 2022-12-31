package com.benjaminstammen.bfi.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.sql.Date
import java.time.LocalDate

@Document("transaction")
data class TransactionEntity(
    @Id val id: String? = null,
    val transactionDate: LocalDate,
    val postedDate: LocalDate?,
    val amount: BigDecimal,
    val categoryId: String,
    val merchantId: String,
    val bankDescription: String?,
    val bankCategory: String?,
    val invoiceIds: List<String> = ArrayList(),
    val note: String?,
    val tags: List<String> = ArrayList()
)
