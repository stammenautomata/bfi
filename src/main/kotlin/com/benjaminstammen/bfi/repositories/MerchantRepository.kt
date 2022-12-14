package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.AccountEntity
import com.benjaminstammen.bfi.entities.MerchantEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface MerchantRepository: MongoRepository<MerchantEntity, String> {
    fun findFirstByName(name: String): MerchantEntity?
    fun findByKnownDescriptionsContaining(description: String): List<MerchantEntity>
}
