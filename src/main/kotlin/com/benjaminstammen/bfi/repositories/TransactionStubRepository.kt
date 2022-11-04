package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.TransactionStubEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionStubRepository: CrudRepository<TransactionStubEntity, Long>