package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.ReceiptEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ReceiptRepository: CrudRepository<ReceiptEntity, Long>