package com.benjaminstammen.bfi.router

import com.benjaminstammen.bfi.entities.Category
import com.benjaminstammen.bfi.repositories.CategoryRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/category")
class CategoryRouter(val categoryRepository: CategoryRepository) {

    @GetMapping
    fun listCategories(): ResponseEntity<List<Category>> {
        return ResponseEntity.ok(categoryRepository.findAll())
    }
}
