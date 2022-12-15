package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.ItemEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface ItemRepository: MongoRepository<ItemEntity, String> {
    fun findAllByTransactionId(transactionId: String): List<ItemEntity>
}
