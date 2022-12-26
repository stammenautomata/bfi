package com.benjaminstammen.bfi.model

import com.benjaminstammen.bfi.entities.CategoryEntity

data class Category(
    val id: String,
    val name: String,
    val note: String?
)

data class CategoryMutableProperties(
    val name: String,
    val note: String?,
)

// Supporting functions

fun toEntity(category: CategoryMutableProperties): CategoryEntity {
    return CategoryEntity(
        name = category.name,
        note = category.note,
    )
}

fun fromEntity(category: CategoryEntity): Category {
    return Category(
        id = category.id!!,
        name = category.name,
        note = category.note
    )
}
