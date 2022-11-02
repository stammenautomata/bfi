package com.benjaminstammen.bfi.router

import com.benjaminstammen.bfi.model.*
import com.benjaminstammen.bfi.repositories.MerchantRepository
import com.benjaminstammen.bfi.translation.fromEntity
import com.benjaminstammen.bfi.translation.toEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/merchant")
class MerchantRouter(val merchantRepository: MerchantRepository) {

    @PostMapping
    fun createMerchant(@RequestBody request: CreateMerchantRequest): ResponseEntity<Merchant> {
        val persistedEntity = merchantRepository.save(toEntity(request))
        return ResponseEntity.ok(fromEntity(persistedEntity))
    }

    @GetMapping
    fun listMerchants() : ResponseEntity<List<Merchant>> {
        return ResponseEntity.ok(merchantRepository.findAll().map { fromEntity(it) })
    }
}