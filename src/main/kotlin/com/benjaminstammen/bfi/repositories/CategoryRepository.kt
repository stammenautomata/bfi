package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.CategoryEntity
import com.benjaminstammen.bfi.entities.MerchantEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface CategoryRepository: MongoRepository<CategoryEntity, String> {
    fun findFirstByName(name: String): CategoryEntity?
}
