package com.benjaminstammen.bfi.router

import com.benjaminstammen.bfi.model.Account
import com.benjaminstammen.bfi.model.CreateAccountRequest
import com.benjaminstammen.bfi.repositories.AccountRepository
import com.benjaminstammen.bfi.translation.fromEntity
import com.benjaminstammen.bfi.translation.toEntity
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@Configuration
class AccountRouter(val accountRepository: AccountRepository) {
    @PostMapping
    fun createAccount(@RequestBody createAccountRequest: CreateAccountRequest): ResponseEntity<Account> {
        val accountEntity = accountRepository.save(toEntity(createAccountRequest))
        return ResponseEntity.ok(fromEntity(accountEntity))
    }

    @GetMapping
    fun listAccounts() : ResponseEntity<List<Account>> {
        val accounts = ArrayList<Account>()
        accountRepository.findAll().mapTo(accounts) { fromEntity(it) }
        return ResponseEntity.ok(accounts)
    }
}