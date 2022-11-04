package com.benjaminstammen.bfi.translation

import com.benjaminstammen.bfi.entities.ReceiptEntity
import com.benjaminstammen.bfi.model.Receipt

fun fromEntity(entity: ReceiptEntity): Receipt {
    return Receipt(
        id = entity.id!!,
        imagePath = entity.imagePath!!,
        imageMd5 = entity.imageMd5!!,
    )
}
