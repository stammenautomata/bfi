package com.benjaminstammen.bfi.router

import com.benjaminstammen.bfi.model.Category
import com.benjaminstammen.bfi.repositories.CategoryRepository
import com.benjaminstammen.bfi.translation.fromEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
class CategoryRouter(val categoryRepository: CategoryRepository) {

    @GetMapping(produces = ["application/json"])
    fun hello(): ResponseEntity<Category> {
        return ResponseEntity.ok(Category(0, "category-name", "note"))
    }

    @GetMapping
    fun listCategories(): ResponseEntity<List<Category>> {
        val categories = ArrayList<Category>()
        categoryRepository.findAll().mapTo(categories) { fromEntity(it) }
        return ResponseEntity.ok(categories)
    }
}