package com.benjaminstammen.bfi.router

import com.benjaminstammen.bfi.model.*
import com.benjaminstammen.bfi.repositories.MerchantDescriptionRepository
import com.benjaminstammen.bfi.repositories.MerchantRepository
import com.benjaminstammen.bfi.translation.fromEntity
import com.benjaminstammen.bfi.translation.toEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/merchant_description")
class MerchantDescriptionRouter(
        val merchantDescriptionRepository: MerchantDescriptionRepository,
        val merchantRepository: MerchantRepository) {

    @PostMapping
    fun createMerchantDescription(@RequestBody request: CreateMerchantDescriptionRequest): ResponseEntity<MerchantDescription> {
        val maybeMerchant = merchantRepository.findById(request.merchantId)
        if (!maybeMerchant.isPresent) {
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Merchant with ID '${request.merchantId}' does not exist."
            )
        }

        val persistedEntity = merchantDescriptionRepository.save(
                toEntity(request, maybeMerchant.get())
        )

        return ResponseEntity.ok(fromEntity(persistedEntity))
    }

    @GetMapping("merchant_description/{description}")
    fun getMerchantForDescription(@PathVariable description: String) : ResponseEntity<MerchantDescription> {
        val maybeMerchantDescription = merchantDescriptionRepository.findById(description)
        if (!maybeMerchantDescription.isPresent) {
            throw ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Merchant Description with description '${description}' does not exist.'}"
            )
        }
        return ResponseEntity.ok(fromEntity(maybeMerchantDescription.get()))
    }

    @GetMapping()
    fun getMerchantDescriptions(@RequestParam description: String?, @RequestParam merchantId: Long?) : ResponseEntity<List<MerchantDescription>> {
        return ResponseEntity.ok(merchantDescriptionRepository.findAll().map { fromEntity(it) }.filter {
            val descriptionMatch = description == null || description == it.description
            val idMatch = merchantId == null || merchantId == it.merchant.id
            descriptionMatch && idMatch
        })
    }
}