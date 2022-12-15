package com.benjaminstammen.bfi

import com.benjaminstammen.bfi.entities.AccountEntity
import com.benjaminstammen.bfi.model.Account
import com.benjaminstammen.bfi.model.AccountMutableProperties

fun toEntity(accountMutableProperties: AccountMutableProperties): AccountEntity {
    return AccountEntity(
        name = accountMutableProperties.name,
        autoTags = accountMutableProperties.autoTags.toList(),
        note = accountMutableProperties.note
    )
}

fun fromEntity(accountEntity: AccountEntity): Account {
    return Account(
        id = accountEntity.id!!,
        name = accountEntity.name,
        autoTags = accountEntity.autoTags.toList(),
        note = accountEntity.note
    )
}
