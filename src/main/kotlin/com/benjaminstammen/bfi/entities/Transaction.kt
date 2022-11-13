package com.benjaminstammen.bfi.entities

import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import java.sql.Date
import javax.persistence.Id

@Document("transaction")
class Transaction(
    @Id val id: String? = null,
    val transactionDate: Date,
    val postedDate: Date?,
    val amount: BigDecimal,
    val categoryId: String,
    val merchantId: String,
    val bankDescription: String?,
    val bankCategory: String?,
    val invoiceIds: List<String> = ArrayList(),
    val splits: List<String> = ArrayList(),
    val note: String?,
    val tags: List<String> = ArrayList()
)
