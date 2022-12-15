package com.benjaminstammen.bfi.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("invoice")
data class InvoiceEntity(
    @Id val id: String? = null,
    val resourceUri: String,
    val md5: String
)
