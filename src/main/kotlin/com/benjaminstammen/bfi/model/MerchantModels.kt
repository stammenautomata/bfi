package com.benjaminstammen.bfi.model

import com.benjaminstammen.bfi.entities.MerchantEntity

data class Merchant(
    val id: String,
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

data class MerchantMutableProperties(
    val name: String,
    val knownDescriptions: List<String> = ArrayList(),
    val defaultCategoryId: String?,
    val note: String?,
    val tags: List<String> = ArrayList(),
    val autoTags: List<String> = ArrayList()
)

// Supporting functions

fun toEntity(merchant: MerchantMutableProperties): MerchantEntity {
    return MerchantEntity(
        name = merchant.name,
        knownDescriptions = merchant.knownDescriptions,
        defaultCategoryId = merchant.defaultCategoryId,
        note = merchant.note,
        tags = merchant.tags,
        autoTags = merchant.autoTags
    )
}

fun fromEntity(merchant: MerchantEntity): Merchant {
    return Merchant(
        id = merchant.id!!,
        name = merchant.name,
        knownDescriptions = merchant.knownDescriptions,
        defaultCategoryId = merchant.defaultCategoryId,
        note = merchant.note,
        tags = merchant.tags,
        autoTags = merchant.autoTags
    )
}

