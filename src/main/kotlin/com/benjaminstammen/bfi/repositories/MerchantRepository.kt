package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.CategoryEntity
import com.benjaminstammen.bfi.entities.MerchantEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MerchantRepository : CrudRepository<MerchantEntity, Long>