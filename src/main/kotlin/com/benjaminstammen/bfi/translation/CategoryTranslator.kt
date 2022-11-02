package com.benjaminstammen.bfi.translation

import com.benjaminstammen.bfi.entities.CategoryEntity
import com.benjaminstammen.bfi.entities.TransactionEntity
import com.benjaminstammen.bfi.model.*

fun fromEntity(entity: CategoryEntity): Category {
    return Category(
            id = entity.id!!,
            name = entity.name!!,
            note = entity.note,
    )
}

fun toEntity(request: CreateCategoryRequest): CategoryEntity {
    val categoryEntity = CategoryEntity()
    categoryEntity.name = request.name
    categoryEntity.note = request.note
    return categoryEntity
}
