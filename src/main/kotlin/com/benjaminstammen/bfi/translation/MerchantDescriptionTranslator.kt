package com.benjaminstammen.bfi.translation

import com.benjaminstammen.bfi.entities.*
import com.benjaminstammen.bfi.model.*

fun fromEntity(entity: MerchantDescriptionEntity): MerchantDescription {
    return MerchantDescription(
            description = entity.description!!,
            merchant = fromEntity(entity.merchant!!),
    )
}

fun toEntity(request: CreateMerchantDescriptionRequest,
             merchant: MerchantEntity): MerchantDescriptionEntity {
    val entity = MerchantDescriptionEntity()
    entity.description = request.description
    entity.merchant = merchant
    return entity
}