package com.benjaminstammen.bfi.controllers

import com.benjaminstammen.bfi.entities.AccountEntity
import com.benjaminstammen.bfi.model.AccountMutableProperties
import com.benjaminstammen.bfi.repositories.AccountRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/account")
class AccountController(val accountRepository: AccountRepository) {
    @PostMapping
    fun createAccount(@RequestBody accountMutableProperties: AccountMutableProperties): ResponseEntity<AccountEntity> {
        val accountEntity = accountRepository.save(
            AccountEntity(
                name = accountMutableProperties.name,
                autoTags = accountMutableProperties.autoTags,
                note = accountMutableProperties.note,
            )
        )
        return ResponseEntity.ok(accountEntity)
    }

    @GetMapping(produces = ["application/json"])
    fun listAccounts(): ResponseEntity<List<AccountEntity>> {
        val accounts = accountRepository.findAll()
        return ResponseEntity.ok(accounts)
    }

    @PostMapping("/{accountId}")
    fun updateAccount(
        @PathVariable("accountId") accountId: String,
        @RequestBody accountProperties: AccountMutableProperties
    ): ResponseEntity<AccountEntity> {

        val maybePersistedAccount = accountRepository.findById(accountId)
        if (!maybePersistedAccount.isPresent) {
            throw ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Account with ID '${accountId}' does not exist."
            )
        }

        val persistedAccount = maybePersistedAccount.get()
        return ResponseEntity.ok(
            accountRepository.save(
                persistedAccount.mergeWithProperties(accountProperties)
            )
        )
    }
}
