package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.CategoryEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface CategoryRepository: MongoRepository<CategoryEntity, String>
