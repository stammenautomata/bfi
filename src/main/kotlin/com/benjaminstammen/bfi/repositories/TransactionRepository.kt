package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.TransactionEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository: CrudRepository<TransactionEntity, Long>