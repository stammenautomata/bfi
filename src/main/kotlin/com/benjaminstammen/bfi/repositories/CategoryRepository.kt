package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.CategoryEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoryRepository : CrudRepository<CategoryEntity, Long>