package com.benjaminstammen.bfi.entities

import com.benjaminstammen.bfi.model.AccountMutableProperties
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("account")
data class AccountEntity(
    @Id val id: String? = null,
    val name: String,
    val autoTags: List<String>,
    val note: String?
) {
    fun mergeWithProperties(mutableProperties: AccountMutableProperties): AccountEntity {
        return AccountEntity(
            id = this.id,
            name= mutableProperties.name,
            autoTags = mutableProperties.autoTags,
            note = mutableProperties.note
        )
    }
}
