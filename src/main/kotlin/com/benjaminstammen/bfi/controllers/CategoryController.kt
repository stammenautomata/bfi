package com.benjaminstammen.bfi.controllers

import com.benjaminstammen.bfi.model.Category
import com.benjaminstammen.bfi.model.CategoryMutableProperties
import com.benjaminstammen.bfi.services.CategoryService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/categories")
class CategoryController(val categoryService: CategoryService) {
    @PostMapping
    fun createCategory(@RequestBody categoryMutableProperties: CategoryMutableProperties): ResponseEntity<Category> {
        return ResponseEntity.ok(categoryService.createCategory(categoryMutableProperties))
    }

    @GetMapping
    fun listCategories(): ResponseEntity<List<Category>> {
        return ResponseEntity.ok(categoryService.listCategories())
    }

    @PostMapping("/{categoryId}")
    fun updateCategory(
        @PathVariable("categoryId") categoryId: String,
        @RequestBody categoryMutableProperties: CategoryMutableProperties
    ): ResponseEntity<Category> {
        return ResponseEntity.ok(
            categoryService.updateCategory(categoryId, categoryMutableProperties))
    }

    @DeleteMapping("/{categoryId}")
    fun deleteAccount(
        @PathVariable("categoryId") categoryId: String
    ): ResponseEntity<Void> {
        categoryService.deleteCategory(categoryId)
        return ResponseEntity.ok().build()
    }
}
