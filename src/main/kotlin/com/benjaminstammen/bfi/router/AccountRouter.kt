package com.benjaminstammen.bfi.router

import com.benjaminstammen.bfi.entities.Account
import com.benjaminstammen.bfi.model.CreateAccountRequest
import com.benjaminstammen.bfi.repositories.AccountRepositoryTwo
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountRouter(val accountRepositoryTwo: AccountRepositoryTwo) {
    @PostMapping
    fun createAccount(@RequestBody createAccountRequest: CreateAccountRequest): ResponseEntity<Account> {
        val accountEntity = accountRepositoryTwo.save(
            Account(
                name = createAccountRequest.name,
                note = createAccountRequest.note,
            )
        )
        return ResponseEntity.ok(accountEntity)
    }

    @GetMapping(produces = ["application/json"])
    fun listAccounts() : ResponseEntity<List<Account>> {
        val accounts = accountRepositoryTwo.findAll()
        return ResponseEntity.ok(accounts)
    }
}
