package com.benjaminstammen.bfi.repositories

import com.benjaminstammen.bfi.entities.AccountEntity
import org.springframework.data.mongodb.repository.MongoRepository

interface AccountRepository: MongoRepository<AccountEntity, String> {
    fun findFirstByName(name: String): AccountEntity?
}
