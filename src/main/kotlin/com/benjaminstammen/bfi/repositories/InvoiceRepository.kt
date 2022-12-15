package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.InvoiceEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface InvoiceRepository: MongoRepository<InvoiceEntity, String>
