package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.Invoice
import org.springframework.data.mongodb.repository.MongoRepository

interface InvoiceRepository: MongoRepository<Invoice, String>
