package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.Category
import org.springframework.data.mongodb.repository.MongoRepository

interface CategoryRepository: MongoRepository<Category, String>
