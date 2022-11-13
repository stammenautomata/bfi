package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.Merchant
import org.springframework.data.mongodb.repository.MongoRepository

interface MerchantRepository: MongoRepository<Merchant, String>
