package com.benjaminstammen.bfi.translation

import com.benjaminstammen.bfi.entities.AccountEntity
import com.benjaminstammen.bfi.entities.CategoryEntity
import com.benjaminstammen.bfi.entities.MerchantEntity
import com.benjaminstammen.bfi.entities.TransactionEntity
import com.benjaminstammen.bfi.model.*

fun fromEntity(entity: MerchantEntity): Merchant {
    return Merchant(
            id = entity.id!!,
            name = entity.name!!,
            note = entity.note,
    )
}

fun toEntity(request: CreateMerchantRequest): MerchantEntity {
    val entity = MerchantEntity()
    entity.name = request.name
    entity.note = request.note
    return entity
}