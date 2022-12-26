package com.benjaminstammen.bfi.entities

import com.benjaminstammen.bfi.model.CategoryMutableProperties
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("category")
data class CategoryEntity(
    @Id val id: String? = null,
    val name: String,
    val note: String?
) {
    fun mergeWithProperties(categoryMutableProperties: CategoryMutableProperties): CategoryEntity {
        return CategoryEntity(
            id = this.id,
            name = categoryMutableProperties.name,
            note = categoryMutableProperties.note
        )
    }
}
