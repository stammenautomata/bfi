package com.benjaminstammen.bfi.entities

import com.benjaminstammen.bfi.model.MerchantMutableProperties
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
) {
    fun mergeWithProperties(merchantMutableProperties: MerchantMutableProperties): MerchantEntity {
        return MerchantEntity(
            id = this.id,
            name = merchantMutableProperties.name,
            knownDescriptions = merchantMutableProperties.knownDescriptions,
            defaultCategoryId = merchantMutableProperties.defaultCategoryId,
            note = merchantMutableProperties.note,
            tags = merchantMutableProperties.tags,
            autoTags = merchantMutableProperties.autoTags
        )
    }
}
