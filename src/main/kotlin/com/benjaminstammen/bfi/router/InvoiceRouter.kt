package com.benjaminstammen.bfi.router

import com.benjaminstammen.bfi.entities.Invoice
import com.benjaminstammen.bfi.repositories.InvoiceRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/invoice")
class InvoiceRouter(val invoiceRepository: InvoiceRepository) {

    @GetMapping
    fun listInvoices(): ResponseEntity<List<Invoice>> {
        return ResponseEntity.ok(invoiceRepository.findAll())
    }
}
