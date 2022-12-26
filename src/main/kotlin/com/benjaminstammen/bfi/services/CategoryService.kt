package com.benjaminstammen.bfi.services;

import com.benjaminstammen.bfi.entities.CategoryEntity
import com.benjaminstammen.bfi.model.CategoryMutableProperties
import com.benjaminstammen.bfi.model.Category
import com.benjaminstammen.bfi.model.fromEntity
import com.benjaminstammen.bfi.model.toEntity

import com.benjaminstammen.bfi.repositories.CategoryRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class CategoryService(val categoryRepository: CategoryRepository) {

    fun createCategory(categoryMutableProperties: CategoryMutableProperties): Category {
        enforceConstraints(categoryMutableProperties)
        val categoryEntity = categoryRepository.save(toEntity(categoryMutableProperties))
        return fromEntity(categoryEntity)
    }

    fun enforceCategoryExists(categoryId: String) {
        if (!categoryRepository.existsById(categoryId)) throw CategoryNotFoundException(categoryId)
    }

    fun getCategory(categoryId: String): Category {
        return fromEntity(categoryRepository.findById(categoryId).orElseThrow { CategoryNotFoundException(categoryId) })
    }

    fun listCategories(): List<Category> {
        return categoryRepository.findAll().map { x -> fromEntity(x) }
    }

    fun updateCategory(categoryId: String, categoryMutableProperties: CategoryMutableProperties): Category {
        val existingCategoryEntity = getCategoryEntityOr404(categoryId)
        enforceConstraints(categoryMutableProperties)

        return fromEntity(
            categoryRepository.save(
                existingCategoryEntity.mergeWithProperties(categoryMutableProperties)
            )
        )
    }

    fun deleteCategory(categoryId: String) {
        val existingCategoryEntity = getCategoryEntityOr404(categoryId);
        categoryRepository.delete(existingCategoryEntity)
    }

    private fun getCategoryEntityOr404(categoryId: String): CategoryEntity {
        return categoryRepository.findByIdOrNull(categoryId)
            ?: throw CategoryNotFoundException(categoryId)
    }

    private fun enforceConstraints(categoryMutableProperties: CategoryMutableProperties) {
        val existingCategory = categoryRepository.findFirstByName(categoryMutableProperties.name);
        if (existingCategory != null) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Category with name '${categoryMutableProperties.name}' already exists."
            )
        }
    }
}
