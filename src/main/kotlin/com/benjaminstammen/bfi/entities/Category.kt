package com.benjaminstammen.bfi.entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id

@Document("category")
class Category(
    @Id val id: String? = null,
    val name: String
)
