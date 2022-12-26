package com.benjaminstammen.bfi.services;

import com.benjaminstammen.bfi.entities.MerchantEntity
import com.benjaminstammen.bfi.model.Merchant
import com.benjaminstammen.bfi.model.MerchantMutableProperties
import com.benjaminstammen.bfi.model.fromEntity
import com.benjaminstammen.bfi.model.toEntity
import com.benjaminstammen.bfi.repositories.MerchantRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class MerchantService(val merchantRepository: MerchantRepository) {

    fun createMerchant(merchantMutableProperties: MerchantMutableProperties): Merchant {
        enforceConstraints(merchantMutableProperties)
        val merchantEntity = merchantRepository.save(toEntity(merchantMutableProperties))
        return fromEntity(merchantEntity)
    }

    fun enforceMerchantExists(merchantId: String) {
        if (!merchantRepository.existsById(merchantId)) throw CategoryNotFoundException(merchantId)
    }

    fun getMerchant(merchantId: String): Merchant {
        return fromEntity(merchantRepository.findById(merchantId).orElseThrow { MerchantNotFoundException(merchantId) })
    }

    fun listMerchants(): List<Merchant> {
        return merchantRepository.findAll().map { x -> fromEntity(x) }
    }

    fun updateMerchant(merchantId: String, merchantMutableProperties: MerchantMutableProperties): Merchant {
        val existingMerchantEntity = getMerchantEntityOr404(merchantId)
        enforceConstraints(merchantMutableProperties)

        return fromEntity(
            merchantRepository.save(
                existingMerchantEntity.mergeWithProperties(merchantMutableProperties)
            )
        )
    }

    fun deleteMerchant(merchantId: String) {
        val existingMerchantEntity = getMerchantEntityOr404(merchantId);
        merchantRepository.delete(existingMerchantEntity)
    }

    private fun getMerchantEntityOr404(merchantId: String): MerchantEntity {
        return merchantRepository.findByIdOrNull(merchantId)
            ?: throw MerchantNotFoundException(merchantId)
    }

    private fun enforceConstraints(merchantMutableProperties: MerchantMutableProperties) {
        val existingMerchant = merchantRepository.findFirstByName(merchantMutableProperties.name);
        if (existingMerchant != null) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Merchant with name '${merchantMutableProperties.name}' already exists."
            )
        }
    }
}
