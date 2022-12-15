package com.benjaminstammen.bfi.controllers

import com.benjaminstammen.bfi.entities.CategoryEntity
import com.benjaminstammen.bfi.repositories.CategoryRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
class CategoryController(val categoryRepository: CategoryRepository) {

    @GetMapping
    fun listCategories(): ResponseEntity<List<CategoryEntity>> {
        return ResponseEntity.ok(categoryRepository.findAll())
    }
}
