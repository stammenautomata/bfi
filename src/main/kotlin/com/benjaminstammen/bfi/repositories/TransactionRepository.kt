package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.TransactionEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface TransactionRepository: MongoRepository<TransactionEntity, String>
