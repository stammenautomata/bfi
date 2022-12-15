package com.benjaminstammen.bfi.controllers

import com.benjaminstammen.bfi.model.Account
import com.benjaminstammen.bfi.model.AccountMutableProperties
import com.benjaminstammen.bfi.services.AccountService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/account")
class AccountController(val accountService: AccountService) {
    @PostMapping
    fun createAccount(@RequestBody accountMutableProperties: AccountMutableProperties): ResponseEntity<Account> {
        return ResponseEntity.ok(accountService.createAccount(accountMutableProperties))
    }

    @GetMapping(produces = ["application/json"])
    fun listAccounts(): ResponseEntity<List<Account>> {
        val accounts = accountService.listAccounts()
        return ResponseEntity.ok(accounts)
    }

    @PostMapping("/{accountId}")
    fun updateAccount(
        @PathVariable("accountId") accountId: String,
        @RequestBody accountProperties: AccountMutableProperties
    ): ResponseEntity<Account> {
        return ResponseEntity.ok(accountService.updateAccount(accountId, accountProperties))
    }

    @DeleteMapping("/{accountId}")
    fun deleteAccount(
        @PathVariable("accountId") accountId: String): ResponseEntity<Void> {
        accountService.deleteAccount(accountId)
        return ResponseEntity.ok().build()
    }
}
