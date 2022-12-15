package com.benjaminstammen.bfi.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("category")
data class CategoryEntity(
    @Id val id: String? = null,
    val name: String
)
