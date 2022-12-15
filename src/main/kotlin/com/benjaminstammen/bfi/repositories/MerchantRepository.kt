package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.MerchantEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface MerchantRepository: MongoRepository<MerchantEntity, String>
