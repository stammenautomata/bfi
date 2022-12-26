package com.benjaminstammen.bfi.model

import com.benjaminstammen.bfi.entities.AccountEntity

data class Account(
    val id: String,
    val name: String,
    val autoTags: List<String>,
    val note: String?
)

data class AccountMutableProperties(
    val name: String,
    val autoTags: List<String> = ArrayList(),
    val note: String?,
)

// Supporting functions

fun toEntity(account: AccountMutableProperties): AccountEntity {
    return AccountEntity(
        name = account.name,
        autoTags = account.autoTags,
        note = account.note
    )
}

fun fromEntity(account: AccountEntity): Account {
    return Account(
        id = account.id!!,
        name = account.name,
        autoTags = account.autoTags,
        note = account.note
    )
}
