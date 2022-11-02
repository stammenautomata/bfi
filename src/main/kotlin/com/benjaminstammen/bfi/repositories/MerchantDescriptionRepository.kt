package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.MerchantDescriptionEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MerchantDescriptionRepository : CrudRepository<MerchantDescriptionEntity, String>