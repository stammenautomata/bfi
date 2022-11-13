package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.Transaction
import org.springframework.data.mongodb.repository.MongoRepository

interface TransactionRepository: MongoRepository<Transaction, String>
