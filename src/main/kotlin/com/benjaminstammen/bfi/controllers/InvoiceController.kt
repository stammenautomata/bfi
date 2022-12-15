package com.benjaminstammen.bfi.controllers

import com.benjaminstammen.bfi.entities.InvoiceEntity
import com.benjaminstammen.bfi.repositories.InvoiceRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/invoice")
class InvoiceController(val invoiceRepository: InvoiceRepository) {

    @GetMapping
    fun listInvoices(): ResponseEntity<List<InvoiceEntity>> {
        return ResponseEntity.ok(invoiceRepository.findAll())
    }
}
