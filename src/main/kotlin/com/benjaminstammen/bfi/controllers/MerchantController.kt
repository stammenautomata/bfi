package com.benjaminstammen.bfi.controllers

import com.benjaminstammen.bfi.entities.MerchantEntity
import com.benjaminstammen.bfi.model.MerchantMutableProperties
import com.benjaminstammen.bfi.repositories.MerchantRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/merchant")
class MerchantController(val merchantRepository: MerchantRepository) {

    @PostMapping
    fun createMerchant(@RequestBody request: MerchantMutableProperties): ResponseEntity<MerchantEntity> {
        val persistedEntity = merchantRepository.save(
            MerchantEntity(
                name = request.name,
                note = request.note,
            )
        )
        return ResponseEntity.ok(persistedEntity)
    }

    @GetMapping
    fun listMerchants() : ResponseEntity<List<MerchantEntity>> {
        return ResponseEntity.ok(merchantRepository.findAll())
    }
}
