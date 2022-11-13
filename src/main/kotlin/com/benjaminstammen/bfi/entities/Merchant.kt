package com.benjaminstammen.bfi.entities

import org.springframework.data.mongodb.core.mapping.Document
import javax.persistence.Id

@Document("merchant")
class Merchant(
    @Id val id: String? = null,
    val name: String,
    val knownDescriptions: List<String> = ArrayList(),
    val defaultCategoryId: String? = null,
    val note: String?,
    val tags: List<String> = ArrayList(),
    val autoTags: List<String> = ArrayList()
)