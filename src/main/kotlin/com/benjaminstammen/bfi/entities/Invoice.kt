package com.benjaminstammen.bfi.entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id

@Document("invoice")
class Invoice(
    @Id val id: String? = null,
    val resourceUri: String,
    val md5: String
)
