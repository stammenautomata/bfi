package com.benjaminstammen.bfi.entities

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("merchant")
data class MerchantEntity(
    @Id val id: String? = null,
    val name: String,
    val knownDescriptions: List<String> = ArrayList(),
    val defaultCategoryId: String? = null,
    val note: String?,
    // Tags that are tied to a merchant
    val tags: List<String> = ArrayList(),
    // Tags that are automatically applied to a transaction under this
    // merchant. Unsure if good idea.
    val autoTags: List<String> = ArrayList()
)
