package com.benjaminstammen.bfi.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal

@Document("item")
data class ItemEntity(
    @Id val id: String? = null,
    val transactionId: String,
    val categoryId: String,
    val name: String,
    val unitAmount: BigDecimal,
    val quantity: Int,
    val tags: List<String> = ArrayList()
)
