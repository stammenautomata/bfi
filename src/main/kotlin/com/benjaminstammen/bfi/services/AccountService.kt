package com.benjaminstammen.bfi.services;

import com.benjaminstammen.bfi.entities.AccountEntity
import com.benjaminstammen.bfi.model.Account
import com.benjaminstammen.bfi.model.AccountMutableProperties
import com.benjaminstammen.bfi.model.fromEntity
import com.benjaminstammen.bfi.model.toEntity
import com.benjaminstammen.bfi.repositories.AccountRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.security.auth.login.AccountNotFoundException

@Service
class AccountService(val accountRepository: AccountRepository) {

    fun createAccount(accountMutableProperties: AccountMutableProperties): Account {
        enforceConstraints(accountMutableProperties)
        val accountEntity = accountRepository.save(toEntity(accountMutableProperties))
        return fromEntity(accountEntity)
    }

    fun listAccounts(): List<Account> {
        return accountRepository.findAll().map { x -> fromEntity(x) }
    }

    fun updateAccount(accountId: String, accountMutableProperties: AccountMutableProperties): Account {
        val existingAccountEntity = getAccountEntityOr404(accountId)
        enforceConstraints(accountMutableProperties)

        return fromEntity(
            accountRepository.save(
                existingAccountEntity.mergeWithProperties(accountMutableProperties)
            )
        )
    }

    fun deleteAccount(accountId: String) {
        val existingAccountEntity = getAccountEntityOr404(accountId);
        accountRepository.delete(existingAccountEntity)
    }

    fun getAccountEntityOr404(accountId: String): AccountEntity {
        return accountRepository.findByIdOrNull(accountId)
            ?: throw AccountNotFoundException(accountId)
    }

    fun enforceConstraints(accountMutableProperties: AccountMutableProperties) {
        val existingAccount = accountRepository.findFirstByName(accountMutableProperties.name);
        if (existingAccount != null) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Account with name '${accountMutableProperties.name}' already exists."
            )
        }
    }
}
