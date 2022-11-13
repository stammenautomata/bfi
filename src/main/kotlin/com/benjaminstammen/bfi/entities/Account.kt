package com.benjaminstammen.bfi.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("account")
class Account(
    @Id val id: String? = null,
    val name: String,
    val note: String?
)
